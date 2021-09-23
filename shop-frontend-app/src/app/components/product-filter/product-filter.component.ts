import {Component, OnInit, Output, EventEmitter, Input} from '@angular/core';
import {ProductFilterDto} from "../../model/product-filter-dto";
import {Category} from "../../model/category";

@Component({
  selector: 'app-product-filter',
  templateUrl: './product-filter.component.html',
  styleUrls: ['./product-filter.component.scss']
})
export class ProductFilterComponent implements OnInit {

  @Input() categories: Category[] = [];

  @Output() filterApplied = new EventEmitter<ProductFilterDto>();

  productFilter: ProductFilterDto = new ProductFilterDto("", 0, 0, 0);

  constructor() { }

  ngOnInit(): void {
  }

  applyFilter() {
    this.filterApplied.emit(this.productFilter);
  }
}
