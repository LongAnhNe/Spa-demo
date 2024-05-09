package com.spa.Online.Spa.controller;

import com.spa.Online.Spa.exception.CartException;
import com.spa.Online.Spa.exception.CartItemException;
import com.spa.Online.Spa.exception.ProductException;
import com.spa.Online.Spa.exception.UserException;
import com.spa.Online.Spa.model.Cart;
import com.spa.Online.Spa.model.CartItem;
import com.spa.Online.Spa.model.User;
import com.spa.Online.Spa.request.AddCartItemRequest;
import com.spa.Online.Spa.request.UpdateCartItemRequest;
import com.spa.Online.Spa.service.CartService;
import com.spa.Online.Spa.service.UserService;
import com.stripe.exception.CardException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CartController {
    @Autowired
    private CartService cartService;
    @Autowired
    private UserService userService;

    @PutMapping("/cart/add")
    public ResponseEntity<CartItem> addItemToCart(@RequestBody AddCartItemRequest req,
                                                  @RequestHeader("Authorization") String jwt) throws UserException, CartException, CartItemException, ProductException {
        CartItem cart = cartService.addItemToCart(req, jwt);
        return ResponseEntity.ok(cart);

    }

    @PutMapping("/cart-item/update")
    public ResponseEntity<CartItem> updateCartItemQuantity(
            @RequestBody UpdateCartItemRequest req,
            @RequestHeader("Authorization") String jwt) throws CartItemException, CartException {
        CartItem cart = cartService.updateCartItemQuantity(req.getCartItemId(), req.getQuantity());
        return ResponseEntity.ok(cart);
    }

    @DeleteMapping("/cart-item/{id}/remove")
    public ResponseEntity<Cart> removeItemFromCart(@PathVariable Long id,
                                                   @RequestHeader("Authorization") String jwt) throws UserException, CartException, CartItemException {

        Cart cart = cartService.removeItemFromCart(id, jwt);
        return ResponseEntity.ok(cart);

    }

    @GetMapping("/cart/total")
    public ResponseEntity<Double> calculateCartTotals(@RequestParam Long cartId,
                                                      @RequestHeader("Authorization") String jwt) throws UserException, CartException, CardException {


        User user = userService.findUserProfileByJwt(jwt);

        Cart cart =cartService.findCardByUserId(user.getId());
        double total = cartService.caculateCartTotal(cart);
        return ResponseEntity.ok(total);
    }

    @GetMapping("/cart/")
    public ResponseEntity<Cart> findUserCart(
            @RequestHeader("Authorization") String jwt) throws UserException, CartException, CardException {
        User user=userService.findUserProfileByJwt(jwt);
        Cart cart = cartService.findCardByUserId(user.getId());
        return ResponseEntity.ok(cart);
    }

    @PutMapping("/cart/clear")
    public ResponseEntity<Cart> cleareCart(
            @RequestHeader("Authorization") String jwt) throws UserException, CartException, CardException {
        User user=userService.findUserProfileByJwt(jwt);
        Cart cart = cartService.ClearCart(user.getId());
        return ResponseEntity.ok(cart);
    }

}

