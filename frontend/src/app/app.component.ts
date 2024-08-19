import {Component} from '@angular/core';
import {RouterOutlet} from '@angular/router';
import {SharedSseComponent} from "./shared-sse/shared-sse.component";

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, SharedSseComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {

}
