import "./PlacesRequestForm.css";
import { useForm, SubmitHandler } from "react-hook-form"
import { places } from "../types/requestTypes";


type Inputs = {
    latitude: number,
    longitude: number,
    radius: number,
  }

const backendUrl = import.meta.env.VITE_BACKEND_URL ? import.meta.env.VITE_BACKEND_URL : "http://localhost:8070";

function PlacesRequestForm({OnApiCall, FocusPosition}: {OnApiCall: (places: places[]) => void, FocusPosition: (position: {lat: number, lng: number}) => void}) {

    const {register, handleSubmit, formState: { errors }, getValues} = useForm<Inputs>()
    const onSubmit: SubmitHandler<Inputs> = (data) => {
        fetch(`${backendUrl}/api/v1/places?latitude=${data.latitude}&longitude=${data.longitude}&radius=${data.radius}`)
        .then((response) => response.json())
        .then((data) => {
            OnApiCall(data.places);
            FocusPosition({lat: getValues("latitude"), lng: getValues("longitude")});
            console.log("Fetched places:", data);
        })
        .catch((error) => {
            console.error("Error fetching places:", error);
        });
    }
  return (
    <div className="places-request-form">
      <form className="request-form" onSubmit={handleSubmit(onSubmit)}>
        <input type="number" step="any" {...register("latitude", {
            required: "Latitude is required",
            min: { value: -90, message: "Latitude must be ≥ -90" },
            max: { value: 90, message: "Latitude must be ≤ 90" }
                })} placeholder="latitude"></input>
        <input type="number" step="any" {...register("longitude", {
            required: "Longitude is required",
            min: {value:-180, message: "Longitude must be ≥ -180"},
            max: {value:180, message: "Longitude must be ≤ 180"}
                })} placeholder="longitude"></input>
        <input type="number" step="any" {...register("radius", {
            required: "Radius is required", 
            min: {value:-0, message: "Radius must be ≥ 0"},
            max: {value:50000, message: "Radius must be ≤ 50000"} 
                })} placeholder="radius"></input>
        <button type="submit">GATHER NEARBY</button>
        {errors.latitude && <span className="error-message">{errors.latitude.message}</span>}
        {errors.longitude && <span className="error-message">{errors.longitude.message}</span>}
        {errors.radius && <span className="error-message">{errors.radius.message}</span>}
      </form>
    </div>
  );
}

export default PlacesRequestForm;