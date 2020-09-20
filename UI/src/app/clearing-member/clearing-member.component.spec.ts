import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ClearingMemberComponent } from './clearing-member.component';

describe('ClearingMemberComponent', () => {
  let component: ClearingMemberComponent;
  let fixture: ComponentFixture<ClearingMemberComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ClearingMemberComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ClearingMemberComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
