import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ModifyUserProfileComponent } from './modify-user-profile.component';

describe('ModifyUserProfileComponent', () => {
  let component: ModifyUserProfileComponent;
  let fixture: ComponentFixture<ModifyUserProfileComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ModifyUserProfileComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(ModifyUserProfileComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
