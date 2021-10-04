import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {AddLineItemDto} from "../model/add-line-item-dto";
import {AllCartDto} from "../model/all-cart-dto";
import {Observable} from "rxjs";
import {LineItem} from "../model/line-item";

@Injectable({
  providedIn: 'root'
})
export class CartService {

  constructor(private http: HttpClient) { }

  public findAll() : Observable<AllCartDto> {
    return this.http.get<AllCartDto>('/api/v1/cart/all');
  }

  public addToCart(dto: AddLineItemDto) {
    return this.http.post('/api/v1/cart', dto)
  }

  public deleteLineItem(dto: LineItem) {
    return this.http.delete('/api/v1/cart', {body: dto})
  }

  public updateLineItem(dto: LineItem) {
    return this.http.put('/api/v1/cart', dto)
  }

  public deleteAll() {
    return this.http.delete('/api/v1/cart/all')
  }

}
