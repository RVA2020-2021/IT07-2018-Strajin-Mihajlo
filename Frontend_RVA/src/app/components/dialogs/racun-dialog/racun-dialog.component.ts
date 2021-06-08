import { Component, Inject, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Subscription } from 'rxjs';
import { Klijent } from 'src/app/models/klijent';
import { Racun } from 'src/app/models/racun';
import { TipRacuna } from 'src/app/models/tipRacuna';
import { RacunService } from 'src/app/services/racun.service';
import { TipRacunaService } from 'src/app/services/tipRacuna.service';

@Component({
  selector: 'app-racun-dialog',
  templateUrl: './racun-dialog.component.html',
  styleUrls: ['./racun-dialog.component.css']
})
export class RacunDialogComponent implements OnInit {

  public flag: number;
  tipoviRacuna: TipRacuna[];
  tipRacunaSubscription: Subscription;

  constructor( public snackBar: MatSnackBar,
    public dialogRef: MatDialogRef<RacunDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: Racun,
    public tipRacunaService: TipRacunaService,
    public racunService: RacunService
  ) { }

  ngOnDestroy(): void {
    this.tipRacunaSubscription.unsubscribe();
  }

  ngOnInit(): void {
    this.tipRacunaSubscription = this.tipRacunaService.getAllTipoviRacuna().subscribe(
      data => {
        this.tipoviRacuna = data;
      }
    ),
    (error: Error) => {
      console.log(error.name + ' ' + error.message);
    }
  }

  compareTo(a,b) {
    return a.id == b.id;
  }

  public add(): void {
    this.racunService.addRacun(this.data).subscribe(() => {
      this.snackBar.open('Racun uspešno dodat.' , 'OK', {
        duration: 2500
      });
    }),
    (error: Error) => {
      console.log(error.name);
      this.snackBar.open('Došlo je do greške prilikom dodavanja racuna. ' , 'Zatvori', {
        duration: 2500
      });
    }
    
  }
  public update(): void {
    this.racunService.updateRacun(this.data).subscribe(() => {
      this.snackBar.open('Racun uspešno izmenjen: ' + this.data.id, 'OK', {
        duration: 2500
      });
    }),
    (error: Error) => {
      console.log(error.name);
      this.snackBar.open('Došlo je do greške prilikom racuna. ' , 'Zatvori', {
        duration: 2500
      });
    }
    
  }
  public delete(): void {
    this.racunService.deleteRacun(this.data.id).subscribe(() => {
      this.snackBar.open('Racun uspešno obrisan: ' + this.data.id, 'OK', {
        duration: 2500
      });
    }),
    (error: Error) => {
      console.log(error.name);
      this.snackBar.open('Došlo je do greške prilikom brisanja racuna. ' , 'Zatvori', {
        duration: 2500
      });
    }
    
  }
  public cancel(): void {
    this.dialogRef.close();
    this.snackBar.open('Odustali ste. ' , 'Zatvori', {
      duration: 1000
    });
  }

}
