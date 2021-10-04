import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {AuthService} from "./auth.service";
import {Credentials} from "../model/credentials";
import {Observable} from "rxjs";
import {Order} from "../model/order";

@Injectable({
  providedIn: 'root'
})
export class OrderService {

  constructor(private http: HttpClient) { }

  public createOrder() {
    return this.http.post<any>('/api/v1/order', {});
  }

  public findOrdersByUser() {
    return this.http.get<Order[]>('/api/v1/order/all');
  }
}
