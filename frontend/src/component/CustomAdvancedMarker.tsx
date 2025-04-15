import React, { useCallback, useState } from 'react';
import { AdvancedMarker, useAdvancedMarkerRef } from '@vis.gl/react-google-maps';
import { InfoWindow } from '@vis.gl/react-google-maps';
import { places } from '../types/requestTypes';
import './CustomAdvancedMarker.css';

const CustomAdvancedMarker = ({position, place} : {position: google.maps.LatLng, place: places}) => {
    const [markerRef, marker] = useAdvancedMarkerRef();
  
    const [infoWindowShown, setInfoWindowShown] = useState(false);
  
    const handleMarkerClick = useCallback(
      () => setInfoWindowShown(isShown => !isShown),
      []
    );
  
    const handleClose = useCallback(() => setInfoWindowShown(false), []);
  
    return (
      <>
        <AdvancedMarker
          ref={markerRef}
          position={position}
          onClick={handleMarkerClick}
        />
        {infoWindowShown && (
          <InfoWindow anchor={marker} onClose={handleClose} 
          headerContent={<span className='info-window-header'>{place.displayName.text}</span>}>
            <div className='info-window-content'>
                <span>{place.formattedAddress}</span>
                <span>{place.internationalPhoneNumber}</span>
            </div>
            <div className='info-window-links'>
                {place.websiteUri && (<a href={place.websiteUri}>Website</a>)}
                {place.googleMapsUri && (<a href={place.googleMapsUri}>Google Maps</a>)}
            </div>
          </InfoWindow>
        )}
      </>
    );
  };

  export default CustomAdvancedMarker;