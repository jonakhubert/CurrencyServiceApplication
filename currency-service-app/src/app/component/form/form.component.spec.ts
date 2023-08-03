import { ComponentFixture, TestBed } from '@angular/core/testing';
import { FormComponent } from './form.component';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormsModule, NgForm } from '@angular/forms';
import { CurrencyService } from 'src/app/service/currency.service';
import { of, throwError } from 'rxjs';
import { ApiError } from 'src/app/interface/api-error';
import { CurrencyResponse } from 'src/app/interface/currency-response';

describe('FormComponent', () => {
  let component: FormComponent;
  let fixture: ComponentFixture<FormComponent>;
  let service: CurrencyService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [FormComponent],
      imports: [HttpClientTestingModule, FormsModule]
    });
    fixture = TestBed.createComponent(FormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
    service = TestBed.inject(CurrencyService);
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should initialize the form with empty fields', () => {
    // arrange

    const currencyInput = fixture.nativeElement.querySelector('[name="currency"]');
    const nameInput = fixture.nativeElement.querySelector('[name="name"]');

    // assert

    expect(currencyInput.value).toBe('');
    expect(nameInput.value).toBe('');
  });

  it('should set message on error with 404 status code', () => {
    // arrange

    const formData: any = {
        value: { currency: '', name: '' },
    };
    const error: ApiError = { path: '', message: 'Currency not found.', statusCode: 404, time: '' };

    spyOn(service, 'getCurrentCurrencyValue').and.returnValue(throwError(error));

    // act & assert
    
    component.onSubmit(formData);
    expect(component.message).toContain('Currency not found.');
  });

  it('should set message on unexpected error', () => {
    // arrange

    const formData: any = {
        value: { currency: 'USD', name: 'Jan Kowalski' },
    };
    const error: ApiError = { path: '', message: 'Internal server error.', statusCode: 500, time: '' };

    spyOn(service, 'getCurrentCurrencyValue').and.returnValue(throwError(error));

    // act & assert

    component.onSubmit(formData);
    expect(component.message).toContain('An unexpected error occurred. Please try again later.');
  });

  it('should set currencyValue and message on successful currency retrieval', () => {
    // arrange

    const formData: any = {
        value: { currency: 'USD', name: 'Jan Kowalski' },
    };
    const response: CurrencyResponse = { value: 3.42 };

    spyOn(service, 'getCurrentCurrencyValue').and.returnValue(of(response));

    // act & assert

    component.onSubmit(formData);
    expect(component.currencyValue).toBe(response.value);
    expect(component.message).toContain('USD: 3.42');
  });

  it('should show validation alert when form is submitted with empty fields', () => {
    // arrange

    const formData: any = {
        value: { currency: '', name: '' },
        valid: false,
        invalid: true,
    };

    spyOn(window, 'alert');

    // act & assert

    component.onSubmit(formData);
    expect(component.currencyValue).toBe(0);
    expect(window.alert).toHaveBeenCalledWith('You have to provide currency and name.');
  });

});