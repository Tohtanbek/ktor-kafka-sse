import {Component, inject, Input, OnInit} from '@angular/core';
import {SseService} from "./SseService";
import {Observable} from "rxjs";
import {AsyncPipe} from "@angular/common";

@Component({
  selector: 'app-shared-sse',
  standalone: true,
  imports: [
    AsyncPipe
  ],
  templateUrl: './shared-sse.component.html',
  styleUrl: './shared-sse.component.css'
})
export class SharedSseComponent implements OnInit{

  @Input({required: true}) sourceUrl?: string

  private sseService = inject(SseService)

  quoteSource$?: Observable<string>

  ngOnInit(): void {
    const randomQuoteUrl = new URL(this.sourceUrl!)
    this.quoteSource$ = this.sseService.createEventSource(randomQuoteUrl)
  }

}
