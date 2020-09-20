import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule} from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { RouterModule} from '@angular/router';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import {MatTableModule} from '@angular/material';
import {MatTabsModule} from '@angular/material/tabs';


import {TableModule} from 'primeng/table';
import {DropdownModule} from 'primeng/dropdown';
import {ButtonModule} from 'primeng/button';
import {TabViewModule} from 'primeng/tabview';
import {InputTextModule} from 'primeng/inputtext';
import {CommonModule} from '@angular/common';
import { HomeComponent } from './home/home.component';
import {CarService} from '../../src/service/carservice';
import { TradeService } from 'src/service/tradeService';
import { TabsComponent } from './tabs/tabs.component';
import { AddTransactionFormComponent } from './add-transaction-form/add-transaction-form.component';
import {DynamicDialogModule} from 'primeng/dynamicdialog';
import { FormsModule } from '@angular/forms';
import { MatTabComponent } from './mat-tab/mat-tab.component';
import {DataViewModule} from 'primeng/dataview';


import{MessagesModule} from 'primeng/messages';
import{MessageModule} from 'primeng/message';
import { MessageService } from 'primeng/api';
import {SidebarModule} from 'primeng/sidebar';
import { ForgotpasswordComponent } from './forgotpassword/forgotpassword.component';
import { ObligationReportComponent } from './obligation-report/obligation-report.component';
import {MatProgressBarModule} from '@angular/material/progress-bar';
import {MatCardModule} from '@angular/material/card';
import {MatInputModule,MatFormFieldModule} from '@angular/material';
import { AddTransactionComponent } from './add-transaction/add-transaction.component';
//here



import {DialogService} from 'primeng/api';


import {OrderListModule} from 'primeng/orderlist';
import {ToastModule} from 'primeng/toast';
import {ChartModule} from 'primeng/chart';

import { ClientviewComponent } from './clientview/clientview.component';
import { AddFundsComponent } from './add-funds/add-funds.component';
import { ViewTransactionDetailsComponent } from './view-transaction-details/view-transaction-details.component';
import { ClientService } from './services/client.service';
import { LoginService } from './services/login.service';
import { ClientSessionService } from './services/client-session.service';
import { NettingDetailsComponent } from './netting-details/netting-details.component';
import { ObligationComponent } from './obligation/obligation.component';
import { CostOfSettlementComponent } from './cost-of-settlement/cost-of-settlement.component';
import { ClearingMemberComponent } from './clearing-member/clearing-member.component';
import {MatToolbarModule} from '@angular/material/toolbar';
import {MatIconModule} from '@angular/material/icon';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    TabsComponent,
    AddTransactionFormComponent,
    MatTabComponent,
    
    LoginComponent,
    ForgotpasswordComponent,
    ObligationReportComponent,
    AddTransactionComponent,
    AddTransactionComponent,
    //here
    
    ClientviewComponent,
    AddFundsComponent,
    ViewTransactionDetailsComponent,
    LoginComponent,
    NettingDetailsComponent,
    ObligationComponent,
    CostOfSettlementComponent,
    ClearingMemberComponent,
  ],
  entryComponents: [
    AddTransactionFormComponent,
    NettingDetailsComponent,
    AddFundsComponent,
    ViewTransactionDetailsComponent,
    ObligationComponent,
    CostOfSettlementComponent
  ],
  imports: [
    
    OrderListModule,
    ChartModule,
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    CommonModule,
    SidebarModule,
    MatIconModule,
    MatInputModule,
    MatFormFieldModule,
    RouterModule.forRoot([
      {path: '',component: LoginComponent},
      {path: 'home',component: MatTabComponent},
      {path: 'client' , component:ClientviewComponent},
      {path:'forgotpassword',component:ForgotpasswordComponent},
      {path:'clientlist',component:ClearingMemberComponent , outlet:"clientInfo"},

   ]),
   DynamicDialogModule,
   MatTableModule,
   TableModule,
   DropdownModule,
   ButtonModule,
   TabViewModule,
   MatTabsModule,
   BrowserAnimationsModule,
   InputTextModule,
   MessagesModule,
   MessageModule,
   DataViewModule,
   MatProgressBarModule,
   MatCardModule,
   ToastModule,
   MatToolbarModule,
  ],
  providers: [
    CarService,
    TradeService,
    MessageService,
    ClientSessionService,
    LoginService,
    ClientService,
    DialogService,
  
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
