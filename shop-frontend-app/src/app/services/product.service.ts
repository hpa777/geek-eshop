import { Injectable } from '@angular/core';
import {HttpClient, HttpParams} from "@angular/common/http";
import {Observable} from "rxjs";
import {Page} from "../model/page";
import {ProductFilterDto} from "../model/product-filter-dto";

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  constructor(private http: HttpClient) { }

  public findAll(productFilter?: ProductFilterDto, page? : number) : Observable<Page> {
    let params = new HttpParams();
    if (productFilter?.categoryId != null) {
      params = params.set("category", productFilter?.categoryId);
    }
    if (productFilter?.namePattern != null) {
      params = params.set('namePattern', productFilter?.namePattern);
    }
    if (productFilter?.minPrice != null && productFilter?.minPrice > 0) {
      params = params.set('minPrice', productFilter?.minPrice);
    }
    if (productFilter?.maxPrice != null && productFilter?.maxPrice > 0) {
      params = params.set('maxPrice', productFilter?.maxPrice);
    }
    params = params.set("page", page != null ? page : 1);
    params = params.set("size", 3);
    return this.http.get<Page>('/api/v1/product/all', { params: params });
  }
}
