import { Component } from '@angular/core';

@Component({
  selector: 'app-encart',
  standalone: true,
  imports: [],
  templateUrl: './encart.component.html',
  styleUrl: './encart.component.css'
})
export class EncartComponent {
  date: String = formatDate(new Date()) ;
  participant: String = '';

}

function formatDate(date: Date): String {
  const day = String(date.getDate()).padStart(2, '0');
  const month = String(date.getMonth() + 1).padStart(2, '0');
  const year = date.getFullYear();

  return `${day}/${month}/${year}`;
}