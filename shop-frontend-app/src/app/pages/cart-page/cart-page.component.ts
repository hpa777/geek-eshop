import { Component, OnInit } from '@angular/core';
import {CartService} from "../../services/cart.service";
import {AllCartDto} from "../../model/all-cart-dto";
import {AuthService} from "../../services/auth.service";
import {OrderService} from "../../services/order.service";

export const CART_URL = 'cart'

@Component({
  selector: 'app-cart-page',
  templateUrl: './cart-page.component.html',
  styleUrls: ['./cart-page.component.scss']
})
export class CartPageComponent implements OnInit {

  content?: AllCartDto;

  constructor(private cartService: CartService,
              public auth: AuthService,
              private orderService: OrderService) { }

  ngOnInit(): void {
    this.cartUpdated();
  }

  cartUpdated() {
    this.cartService.findAll().subscribe(
      res => {
        this.content = res;
      }
    )
  }

  deleteAll() {
    this.cartService.deleteAll().subscribe(
      res => {
        this.content = undefined;
      }
    )
  }

  createOrder() {
    this.orderService.createOrder().subscribe(
      res => {
        this.deleteAll();
      }
    )
  }
}
