import {Component, OnInit} from '@angular/core';
import {MatTableDataSource} from '@angular/material/table';
import {GetemployeesService} from '../services/getemployees.service';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Router } from '@angular/router';
import { routerTransition } from '../router.transitions';
import { Constants } from '../Constants';
import {Http, HttpModule} from '@angular/http';
import { NotificationsService } from 'angular2-notifications';

/**
 * @title Table with filtering
 */

@Component({
  selector: 'app-filterlist',
  styleUrls: ['filterlist.component.css'],
  templateUrl: 'filterlist.component.html',
  animations: [routerTransition()]
})

export class TableFilter implements OnInit {
  error;
  employees;
  employee;
  names;
  dataSource;
  logdata;
  wasClicked = false;
  buttonColor: string = '#fff';
  issuesdata;
  selectedissue;
  employeeID : number;
  id;





  constructor(public getservice : GetemployeesService, private _service: NotificationsService  ) {
    getservice.getAllEmployees().subscribe(data => {
      this.employees = data;
      this.dataSource = new MatTableDataSource(this.employees);
      this.employees.employeeID = this.id;
      console.log(this.employees);

    });

    this.getservice.getAllEmployers().subscribe(data =>{

      this._service.success("data is loaded");
      this.logdata = data;
      console.log(data);

    });

    getservice.getAllIssues().subscribe(data =>{
      this.issuesdata = data;
    })
  }


  linkEmployees(id: number){
    console.log(id);

      this.getservice.employeeToEmployer(id).subscribe(data =>{
        this.logdata = data;
        console.log("werknemer toegevoegd");

      this.buttonColor = '#8BC34A';
    console.log(this.id);
  });
  }

  displayIssues(id: number){
    for (let x = 0; x < this.issuesdata.length(); x++){
      if (this.issuesdata.x["employee"] = id){
        this.selectedissue = this.issuesdata.x;
      }
    }
  }


  displayedColumns = ['name','Lastname'];

  applyFilter(filterValue: string) {
    filterValue = filterValue.trim(); // Remove whitespace
    filterValue = filterValue.toLowerCase(); // MatTableDataSource defaults to lowercase matches
    this.dataSource.filter = filterValue;
  }
  ngOnInit() {
  }
}





