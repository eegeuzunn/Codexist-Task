import { APIProvider, Map} from '@vis.gl/react-google-maps';
import PlacesRequestForm from './component/PlacesRequestForm';
import {useState} from 'react';
import {places} from './types/requestTypes';
import CustomAdvancedMarker from './component/CustomAdvancedMarker';
import MapPanToSearch from './component/MapPanToSearch';

const App = () => {

  const [placesList, setPlacesList] = useState<places[]>([]);
  const [centerPosition, setCenterPosition] = useState<{ lat: number; lng: number }>({
    lat: 41.0176747,
    lng: 28.8761073,
  });

  return (
    <>
      <PlacesRequestForm OnApiCall={setPlacesList} FocusPosition={setCenterPosition}/>
      <APIProvider apiKey={import.meta.env.VITE_GOOGLE_MAPS_API_KEY}>
        <Map
          mapId="c361ea20f6dc267f"
          style={{width: '100vw', height: '100vh'}}
          defaultCenter={{ lat: centerPosition.lat, lng: centerPosition.lng}}
          defaultZoom={15}
          gestureHandling={'greedy'}
          disableDefaultUI={true}
        >
          {placesList && (placesList.map((place) => {
            return (
              <CustomAdvancedMarker position={
                new google.maps.LatLng(place.location.latitude, place.location.longitude)
              }
              place={place}
              />
            );
          }
          ))}
        </Map>
        <MapPanToSearch centerPosition={centerPosition} />
      </APIProvider>
    </>
  );
}
  
  
export default App
