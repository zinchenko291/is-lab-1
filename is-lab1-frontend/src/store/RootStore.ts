import { VehiclesStore } from './VehiclesStore';
import { CoordinatesStore } from './CoordinatesStore';
import { WebSocketStore } from './WebSocketStore';

export class RootStore {
  vehiclesStore: VehiclesStore;
  coordinatesStore: CoordinatesStore;
  webSocketStore: WebSocketStore;

  constructor() {
    this.vehiclesStore = new VehiclesStore(this);
    this.coordinatesStore = new CoordinatesStore(this);
    this.webSocketStore = new WebSocketStore(this);
    this.webSocketStore.connect('ws://localhost:8080/api/ws');
  }
}
