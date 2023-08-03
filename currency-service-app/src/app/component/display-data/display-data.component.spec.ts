import { ComponentFixture, TestBed, fakeAsync, tick, waitForAsync } from '@angular/core/testing';
import { DisplayDataComponent } from './display-data.component';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormsModule } from '@angular/forms';
import { CurrencyService } from 'src/app/service/currency.service';
import { of } from 'rxjs';
import { UserEntry } from 'src/app/interface/user-entry';

describe('DisplayDataComponent', () => {
  let component: DisplayDataComponent;
  let fixture: ComponentFixture<DisplayDataComponent>;
  let service: CurrencyService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [DisplayDataComponent],
      imports: [HttpClientTestingModule, FormsModule],
    });
    fixture = TestBed.createComponent(DisplayDataComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
    service = TestBed.inject(CurrencyService);
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should create a table with user entries', () => {
    // arrange

    const userEntries: UserEntry[] = [
        { name: 'John Doe', date: '2023-08-03T12:34:56Z', currency: 'USD', value: 3.42 },
        { name: 'Jane Smith', date: '2023-08-03T12:34:56Z', currency: 'EUR', value: 4.32 },
        { name: 'Jane Smith', date: '2023-08-03T12:34:56Z', currency: 'EUR', value: 4.32 }
      ];

    spyOn(service, 'getUserEntries').and.returnValue(of(userEntries));

    // act & assert
    
    component.ngOnInit();
    fixture.detectChanges();

    const tableRows = fixture.nativeElement.querySelectorAll('tbody tr');
    expect(tableRows.length).toEqual(userEntries.length);

    tableRows.forEach((row: any, rowIndex: number) => {
        const cells = row.querySelectorAll('td');
        const userEntry = userEntries[rowIndex];
        expect(cells[0].textContent.trim()).toEqual(userEntry.name);
        expect(cells[1].textContent.trim()).toEqual(userEntry.date);
        expect(cells[2].textContent.trim()).toEqual(userEntry.currency);
        expect(cells[3].textContent.trim()).toEqual(userEntry.value.toString());
      });
  });

});