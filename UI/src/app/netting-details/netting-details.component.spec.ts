import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { NettingDetailsComponent } from './netting-details.component';

describe('NettingDetailsComponent', () => {
  let component: NettingDetailsComponent;
  let fixture: ComponentFixture<NettingDetailsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ NettingDetailsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(NettingDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
