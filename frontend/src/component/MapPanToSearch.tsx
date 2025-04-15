import { useMap } from "@vis.gl/react-google-maps";
import { useEffect } from "react";

function MapPanToSearch({centerPosition}: {centerPosition: {lat: number, lng: number}}) {

  const map = useMap();

  useEffect(() => {
    if (map) {
      map.panTo(new google.maps.LatLng(centerPosition.lat, centerPosition.lng));
    }
  }, [centerPosition]);

  return null;
}
export default MapPanToSearch;