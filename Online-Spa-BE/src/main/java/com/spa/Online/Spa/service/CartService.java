package com.spa.Online.Spa.service;

import com.spa.Online.Spa.exception.CartException;
import com.spa.Online.Spa.exception.CartItemException;
import com.spa.Online.Spa.exception.ProductException;
import com.spa.Online.Spa.exception.UserException;
import com.spa.Online.Spa.model.Cart;
import com.spa.Online.Spa.model.CartItem;
import com.spa.Online.Spa.request.AddCartItemRequest;
import com.stripe.exception.CardException;
import jakarta.persistence.Id;

public interface CartService {
    public CartItem addItemToCart(AddCartItemRequest req, String jwt) throws UserException, CartException, CartItemException, ProductException;

    public CartItem updateCartItemQuantity(Long cartItemId, int quantity) throws CartException, CartItemException;
    public Cart removeItemFromCart(Long cartItemId, String jwt) throws UserException, CartException, CartItemException;

    public Long caculateCartTotal(Cart cart) throws UserException;
    public Cart findCardByUserId(Long userId) throws CardException, UserException, CartException;
    public Cart ClearCart(Long userId) throws UserException, CardException, CartException;


}


