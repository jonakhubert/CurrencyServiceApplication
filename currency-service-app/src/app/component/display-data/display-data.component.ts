import { Component, OnInit } from '@angular/core';
import { ApiError } from 'src/app/interface/api-error';
import { UserEntry } from 'src/app/interface/user-entry';
import { CurrencyService } from 'src/app/service/currency.service';

@Component({
  selector: 'app-display-data',
  templateUrl: './display-data.component.html',
  styleUrls: ['./display-data.component.scss']
})
export class DisplayDataComponent implements OnInit {
  
  userEntries: UserEntry[] = [];

  constructor(private currencyService: CurrencyService) {}

  ngOnInit(): void {
    this.currencyService.getUserEntries().subscribe(
      (userEntries: UserEntry[]) => {
        this.userEntries = userEntries;
      },
      (error: ApiError) => {
        console.log("Error " + error);
      }
    )
  }
}
