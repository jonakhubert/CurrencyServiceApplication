import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NavbarComponent } from './navbar.component';

describe('NavbarComponent', () => {
  let component: NavbarComponent;
  let fixture: ComponentFixture<NavbarComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [NavbarComponent]
    });
    fixture = TestBed.createComponent(NavbarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should display the application name', () => {
    const nameElement: HTMLElement = fixture.nativeElement.querySelector(".navbar-brand");
    expect(nameElement.textContent).toContain('RecruitmentTask-xCode');
  });

  it('should contain display data button', () => {
    const displayButton: HTMLElement = fixture.nativeElement.querySelector(".btn");
    expect(displayButton.textContent).toContain('Display data');
  });
});