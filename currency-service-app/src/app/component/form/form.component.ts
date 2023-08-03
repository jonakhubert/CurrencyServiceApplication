import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { ApiError } from 'src/app/interface/api-error';
import { CurrencyRequest } from 'src/app/interface/currency-request';
import { CurrencyResponse } from 'src/app/interface/currency-response';
import { CurrencyService } from 'src/app/service/currency.service';

@Component({
  selector: 'app-form',
  templateUrl: './form.component.html',
  styleUrls: ['./form.component.scss']
})

export class FormComponent implements OnInit {
  currencyValue: number = 0;
  message: string = "";

  constructor(private currencyService: CurrencyService) {}

  ngOnInit(): void {}

  onSubmit(formData: NgForm): void {
    if(formData.invalid) {
      this.currencyValue = 0;
      alert("You have to provide currency and name.");
      return;
    }

    const request: CurrencyRequest = {
      currency: formData.value.currency,
      name: formData.value.name
    };

    this.currencyService.getCurrentCurrencyValue(request).subscribe(
      (response: CurrencyResponse) => {
        this.currencyValue = response.value;
        this.message = `${request.currency.toUpperCase()}: ${this.currencyValue}`;
      },
      (error: ApiError) => {
        console.error('Error:', error);
        if (error.statusCode === 404) {
          this.message = `${error.message}`;
        } else {
          this.message = 'An unexpected error occurred. Please try again later.';
        }
      }
    );
  }
}