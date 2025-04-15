import {AdvancedMarker, APIProvider, Map, Marker, InfoWindow} from '@vis.gl/react-google-maps';
import PlacesRequestForm from './component/PlacesRequestForm';
import {useCallback, useState} from 'react';
import {places} from './types/requestTypes';
import AdvancedMarkerWithRef from './component/AdvancedMarkerWithRef';
import CustomAdvancedMarker from './component/CustomAdvancedMarker';

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
          {placesList.map((place) => {
            return (
              <CustomAdvancedMarker position={
                new google.maps.LatLng(place.location.latitude, place.location.longitude)
              }
              place={place}
              />
            );
          }
          )}
        </Map>
      </APIProvider>
    </>
  );
}
  
  
export default App
