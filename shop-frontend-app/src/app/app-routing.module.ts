import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { PRODUCT_GALLERY_URL, ProductGalleryPageComponent} from "./pages/product-gallery-page/product-gallery-page.component";
import {CART_URL, CartPageComponent} from "./pages/cart-page/cart-page.component";
import {AuthGuard} from "./helpers/auth.guard";
import {LOGIN_URL, LoginPageComponent} from "./pages/login-page/login-page.component";

const routes: Routes = [
  {path: "", pathMatch: "full", redirectTo: PRODUCT_GALLERY_URL},
  {path: PRODUCT_GALLERY_URL, component: ProductGalleryPageComponent},
  {path: LOGIN_URL, component: LoginPageComponent},
  {path: CART_URL, component: CartPageComponent, canActivate: [AuthGuard]}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
