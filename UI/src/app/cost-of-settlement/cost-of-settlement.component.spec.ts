import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CostOfSettlementComponent } from './cost-of-settlement.component';

describe('CostOfSettlementComponent', () => {
  let component: CostOfSettlementComponent;
  let fixture: ComponentFixture<CostOfSettlementComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CostOfSettlementComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CostOfSettlementComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
