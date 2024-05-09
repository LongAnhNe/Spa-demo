package com.spa.Online.Spa.service;

import com.spa.Online.Spa.exception.CartException;
import com.spa.Online.Spa.exception.CartItemException;
import com.spa.Online.Spa.exception.ProductException;
import com.spa.Online.Spa.exception.UserException;
import com.spa.Online.Spa.model.Cart;
import com.spa.Online.Spa.model.CartItem;
import com.spa.Online.Spa.model.Product;
import com.spa.Online.Spa.model.User;
import com.spa.Online.Spa.repository.CartItemRepository;
import com.spa.Online.Spa.repository.CartRepository;
import com.spa.Online.Spa.repository.ProductRepository;
import com.spa.Online.Spa.request.AddCartItemRequest;
import com.stripe.exception.CardException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartServiceImplement implements CartService{
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private CartItemRepository cartItemRepository;
    private ProductRepository productRepository;

    @Override
    public CartItem addItemToCart(AddCartItemRequest req, String jwt) throws UserException, CartException, CartItemException, ProductException {
        User user = userService.findUserProfileByJwt(jwt);
        Optional<Product> productList = productRepository.findById(req.getMenuItemId());
        if(productList.isEmpty()){
            throw new ProductException("List Product not found");
        }
        Cart cart = findCardByUserId(user.getId());
        for(CartItem cartItem : cart.getItems()){
            if (cartItem.getProduct().equals(productList.get())) {

                int newQuantity = cartItem.getQuantity() + req.getQuantity();
                return updateCartItemQuantity(cartItem.getId(),newQuantity);
            }
        }
        CartItem newCartItem = new CartItem();
        newCartItem.setProduct(productList.get());
        newCartItem.setQuantity(req.getQuantity());
        newCartItem.setCart(cart);
        newCartItem.setIngredients(req.getIngredients());
        newCartItem.setTotalPrice(req.getQuantity()*productList.get().getPrice());

        CartItem savedItem=cartItemRepository.save(newCartItem);
        cart.getItems().add(savedItem);
        cartRepository.save(cart);

        return savedItem;
    }

    @Override
    public CartItem updateCartItemQuantity(Long cartItemId, int quantity) throws  CartItemException {
        Optional<CartItem> cartItem=cartItemRepository.findById(cartItemId);
        if(cartItem.isEmpty()) {
            throw new CartItemException("cart item not exist with id "+cartItemId);
        }
        //cartItem.get().setQuantity(quantity);
        cartItem.get().setQuantity( quantity);
        cartItem.get().setTotalPrice((cartItem.get().getProduct().getPrice()*quantity));
        return cartItemRepository.save(cartItem.get());
    }

    @Override
    public Cart removeItemFromCart(Long cartItemId, String jwt) throws CartException, UserException, CartItemException {
        User user = userService.findUserProfileByJwt(jwt);

        Cart cart = findCardByUserId(user.getId());

        Optional<CartItem> cartItem=cartItemRepository.findById(cartItemId);

        if(cartItem.isEmpty()) {
            throw new CartItemException("cart item not exist with id "+cartItemId);
        }

        cart.getItems().remove(cartItem.get());
        return cartRepository.save(cart);
    }


    @Override
    public Long caculateCartTotal(Cart cart) throws UserException {

        Long total = 0L;
        for (CartItem cartItem : cart.getItems()) {
            total += cartItem.getProduct().getPrice() * cartItem.getQuantity();
        }
        return total;
    }

    @Override
    public Cart findCardByUserId(Long userId) throws  UserException, CartException {
        Optional<Cart> opt=cartRepository.findByCustumer_Id(userId);

        if(opt.isPresent()) {
            return opt.get();
        }
        throw new CartException("cart not found");
    }
    public Cart findCartById(Long id) throws CartException {
        Optional<Cart> cart = cartRepository.findById(id);
        if(cart.isPresent()) {
            return cart.get();
        }
        throw new CartException("Cart not found with the id "+id);
    }

    @Override
    public Cart ClearCart(Long userId) throws UserException, CartException {
        Cart cart=findCardByUserId(userId);

        cart.getItems().clear();
        return cartRepository.save(cart);
    }
}
