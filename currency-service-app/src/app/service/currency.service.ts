import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { CurrencyRequest } from '../interface/currency-request';
import { Observable, catchError, throwError } from 'rxjs';
import { CurrencyResponse } from '../interface/currency-response';
import { UserEntry } from '../interface/user-entry';

@Injectable({ providedIn: 'root' })

export class CurrencyService {

  private readonly apiUrl = 'http://localhost:8080/currencies';

  constructor(private http: HttpClient) {}

  public getCurrentCurrencyValue(request: CurrencyRequest): Observable<CurrencyResponse> {
    return this.http.post<CurrencyResponse>(`${this.apiUrl}/get-current-currency-value-command`, request)
      .pipe(
        catchError(this.handleError)
      );
  }

  public getUserEntries(): Observable<UserEntry[]> {
    return this.http.get<UserEntry[]>(`${this.apiUrl}/requests`)
      .pipe(
        catchError(this.handleError)
      );
  }

  private handleError(error: HttpErrorResponse): Observable<never> {
    console.log(error);
    return throwError(`An error occured - Error code: ${error.status}`);
  }
}
