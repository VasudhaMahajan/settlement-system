import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ObligationReportComponent } from './obligation-report.component';

describe('ObligationReportComponent', () => {
  let component: ObligationReportComponent;
  let fixture: ComponentFixture<ObligationReportComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ObligationReportComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ObligationReportComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
