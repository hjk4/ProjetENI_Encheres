import { Component } from '@angular/core';
import { EncartComponent } from "../encart/encart.component";
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-account-creation',
  standalone: true,
  imports: [EncartComponent, RouterLink],
  templateUrl: './account-creation.component.html',
  styleUrl: './account-creation.component.css'
})
export class AccountCreationComponent {

}
