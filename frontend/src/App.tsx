import {AdvancedMarker, APIProvider, Map, Marker} from '@vis.gl/react-google-maps';
import PlacesRequestForm from './component/PlacesRequestForm';
import {useState} from 'react';
import {places} from './types/requestTypes';

const App = () => {

  const [placesList, setPlacesList] = useState<places[]>([]);

  return (
    <>
      <PlacesRequestForm OnApiCall={setPlacesList} />
      <APIProvider apiKey={import.meta.env.VITE_GOOGLE_MAPS_API_KEY}>
        <Map
          mapId="c361ea20f6dc267f"
          style={{width: '100vw', height: '100vh'}}
          defaultCenter={{lat: 41.017674700000008, lng: 28.876107299999997}}
          defaultZoom={15}
          gestureHandling={'greedy'}
          disableDefaultUI={true}
        >
          {placesList && (placesList.length > 0) && placesList.map((place, index) => (
            <AdvancedMarker
              key={index}
              position={{
                lat: place.location.latitude,
                lng: place.location.longitude,
              }}
              onClick={() => {
                window.open(place.googleMapsUri, '_blank');
              }
            }
              />
          ))}
        </Map>
      </APIProvider>
    </>
  );
}
  
  
export default App
