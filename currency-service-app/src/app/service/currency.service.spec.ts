import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { CurrencyService } from './currency.service';
import { CurrencyRequest } from '../interface/currency-request';
import { CurrencyResponse } from '../interface/currency-response';
import { HttpErrorResponse } from '@angular/common/http';
import { ApiError } from '../interface/api-error';
import { throwError } from 'rxjs';
import { UserEntry } from '../interface/user-entry';

describe('CurrencyServerService', () => {
  let service: CurrencyService;
  let httpTestingController: HttpTestingController;
  const apiUrl = 'http://localhost:8080/currencies';
  let request: CurrencyRequest;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [CurrencyService]
    });

    service = TestBed.inject(CurrencyService);
    httpTestingController = TestBed.inject(HttpTestingController);
    request = { currency: "EUR", name: "Jan Kowalski" };
  });

  it('should make a POST request and return CurrencyResponse on success', () => {
    // arrange

    const expectedResponse: CurrencyResponse = { value: 4.32 };

    // act & assert

    service.getCurrentCurrencyValue(request).subscribe(response => {
      expect(response).toEqual(expectedResponse);
    });

    const req = httpTestingController.expectOne(`${apiUrl}/get-current-currency-value-command`);
    expect(req.request.method).toEqual('POST');
    req.flush(expectedResponse);
  });

  it(`should return HTTP 404 error if currency not found`, () => {
    // arrange

    const request: CurrencyRequest = { currency: "XYZ", name: "Jan Kowalski" };
    const apiError: ApiError = {
      path: `${apiUrl}/get-current-currency-value-command`,
      message: `${request.currency} not found.`,
      statusCode: 404,
      time: '023-08-03T12:34:56Z'

    };
    const errorResponse = new HttpErrorResponse({
      error: apiError,
      status: apiError.statusCode,
      statusText: 'Not found.'
    });

    // act and assert

    spyOn(service, 'getCurrentCurrencyValue').and.returnValue(throwError(errorResponse));

    service.getCurrentCurrencyValue(request).subscribe(() => {
        fail('Expected an error response, but got a success response.');
      },
      (error) => {
        expect(error.status).toBe(404);
        expect(error.error).toEqual(apiError);
      }
    );
  });

  it(`should return HTTP 500 error if there is no connection`, () => {
    // arrange

    const apiError: ApiError = {
      path: `${apiUrl}/get-current-currency-value-command`,
      message: "Internal server Error",
      statusCode: 500,
      time: '023-08-03T12:34:56Z'

    };
    const errorResponse = new HttpErrorResponse({
      error: apiError,
      status: apiError.statusCode,
      statusText: 'Internal Server Error'
   });

    // act and assert

    spyOn(service, 'getCurrentCurrencyValue').and.returnValue(throwError(errorResponse));

    service.getCurrentCurrencyValue(request).subscribe(() => {
        fail('Expected an error response, but got a success response.');
      },
      (error) => {
        expect(error.status).toBe(500);
        expect(error.error).toEqual(apiError);
      }
    );
  });

  it('should make a GET request and return UserEntry table on success', () => {
    // arrange

    const expectedResponse: UserEntry[] = [
      { currency: 'EUR', name: 'Jan Kowalski', date: '2023-08-03T12:34:56Z', value: 4.31 },
      { currency: 'USD', name: 'Jan Kowalski', date: '2023-08-03T12:34:56Z', value: 3.54 },
    ];

    // act & assert

    service.getUserEntries().subscribe((response) => {
      expect(response).toEqual(expectedResponse);
    });

    const req = httpTestingController.expectOne(`${apiUrl}/requests`);
    expect(req.request.method).toEqual('GET');
    req.flush(expectedResponse);
  });

});
