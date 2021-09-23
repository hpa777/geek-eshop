import { Component, OnInit } from '@angular/core';
import {Product} from "../../model/product";
import {ProductService} from "../../services/product.service";
import {Page} from "../../model/page";
import {CategoryService} from "../../services/category.service";
import {Category} from "../../model/category";
import {ProductFilterDto} from "../../model/product-filter-dto";

export const PRODUCT_GALLERY_URL = 'product';

@Component({
  selector: 'app-product-gallery-page',
  templateUrl: './product-gallery-page.component.html',
  styleUrls: ['./product-gallery-page.component.scss']
})
export class ProductGalleryPageComponent implements OnInit {

  products: Product[] = [];

  page? : Page;

  productFilter?: ProductFilterDto;

  pageNumber: number = 1;

  category: Category[] = [];

  constructor(private productService: ProductService,
              private categoryService: CategoryService) { }

  ngOnInit(): void {
    this.retrieveProduct();
    this.categoryService.findAll()
      .subscribe(
        res => {
          this.category = res
        }
      );
  }

  private retrieveProduct() {
    this.productService.findAll()
      .subscribe(
        res => {
          this.page = res;
          this.products = res.content;
        },
      error => {
          console.log(`Can't load products ${error}`);
      });
  }

  filterApplied($event: ProductFilterDto) {
    console.log($event);
    this.productFilter = $event;
    this.productService.findAll($event, 1)
      .subscribe(
        res => {
          this.page = res;
          this.products = res.content;
          this.pageNumber = 1;
        },
        err => {
          console.log(`Can't load products ${err}`);
        });
  }

  goToPage($event: number) {
    this.productService.findAll(this.productFilter, $event)
      .subscribe(
        res => {
          this.page = res;
          this.products = res.content;
          this.pageNumber = res.number + 1;
        },
        err => {
          console.log(`Can't load products ${err}`);
        });
  }

}
