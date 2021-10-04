import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {LineItem} from "../../model/line-item";
import {CartService} from "../../services/cart.service";
import {Page} from "../../model/page";


@Component({
  selector: '[app-cart-item]',
  templateUrl: './cart-item.component.html',
  styleUrls: ['./cart-item.component.scss']
})
export class CartItemComponent implements OnInit {

  @Output() updated = new EventEmitter();

  @Input()
  private _lineItem?: LineItem;

  qty: number = 0;

  constructor(private cartService: CartService) {
  }

  ngOnInit(): void {
  }

  @Input() rowNumber: number = 0;

  @Input()
  set lineItem(value: LineItem | undefined) {
    this._lineItem = value;
    this.qty = value ? value.qty : 0;
  }

  get lineItem(): LineItem | undefined {
    return this._lineItem;
  }

  updateLineItem() {
    if (this._lineItem) {
      this._lineItem.qty = this.qty;
      this.cartService.updateLineItem(this._lineItem)
        .subscribe(
          res => {
            this.updated.emit();
          },
          error => {
            console.log(error)
          }
        )
    }
  }

  deleteLineItem() {
    if (this._lineItem) {
      this.cartService.deleteLineItem(this._lineItem)
        .subscribe(
          res => {
            this.updated.emit();
          },
          error => {
            console.log(error)
          }
        )
    }
  }


}
