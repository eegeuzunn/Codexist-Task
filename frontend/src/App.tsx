import {AdvancedMarker, APIProvider, Map, Marker} from '@vis.gl/react-google-maps';

const App = () => (
  <APIProvider apiKey={import.meta.env.VITE_GOOGLE_MAPS_API_KEY}>
    <Map
      mapId="c361ea20f6dc267f"
      style={{width: '100vw', height: '100vh'}}
      defaultCenter={{lat: 41.017674700000008, lng: 28.876107299999997}}
      defaultZoom={15}
      gestureHandling={'greedy'}
      disableDefaultUI={true}
    >
      <AdvancedMarker position={{lat: 41.017674700000008, lng: 28.876107299999997} }/>
    </Map>
  </APIProvider>
);
export default App
