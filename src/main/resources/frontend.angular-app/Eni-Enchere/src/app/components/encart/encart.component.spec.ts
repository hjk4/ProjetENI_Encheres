import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EncartComponent } from './encart.component';

describe('EncartComponent', () => {
  let component: EncartComponent;
  let fixture: ComponentFixture<EncartComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [EncartComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(EncartComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
