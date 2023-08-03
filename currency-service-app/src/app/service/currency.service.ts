import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { CurrencyRequest } from '../interface/currency-request';
import { Observable, catchError, throwError } from 'rxjs';
import { CurrencyResponse } from '../interface/currency-response';
import { UserEntry } from '../interface/user-entry';
import { ApiError } from '../interface/api-error';

@Injectable({ providedIn: 'root' })

export class CurrencyService {

  private readonly apiUrl = 'http://localhost:8080/currencies';

  constructor(private http: HttpClient) {}

  public getCurrentCurrencyValue(request: CurrencyRequest): Observable<CurrencyResponse> {
    return this.http.post<CurrencyResponse>(`${this.apiUrl}/get-current-currency-value-command`, request)
      .pipe(
        catchError((error: HttpErrorResponse) => {
          return throwError(this.buildApiError(error));
        })
      );
  }

  public getUserEntries(): Observable<UserEntry[]> {
    return this.http.get<UserEntry[]>(`${this.apiUrl}/requests`)
      .pipe(
        catchError((error: HttpErrorResponse) => {
          return throwError(this.buildApiError(error));
        })
      );
  }

  private buildApiError(error: HttpErrorResponse) {
    const apiError: ApiError = {
      path: error.error.path,
      message: error.error.message,
      statusCode: error.error.statusCode,
      time: error.error.time
    };

    return apiError;
  }
}
