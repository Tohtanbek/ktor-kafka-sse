import {Observable} from "rxjs";
import {Injectable} from "@angular/core";

@Injectable({providedIn: 'root'})
export class SseService {

  createEventSource(url: URL): Observable<string> {
    const eventSource = new EventSource(url)

    return new Observable<string>(observer => {
      eventSource.onmessage = (event) => {
        const receivedData = event.data
        observer.next(receivedData)
      }
    })
  }
}
