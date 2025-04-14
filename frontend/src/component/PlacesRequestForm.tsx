import "./PlacesRequestForm.css";
import { useForm, SubmitHandler } from "react-hook-form"
import { places } from "../types/requestTypes";

type Inputs = {
    latitude: number,
    longitude: number,
    radius: number,
  }

const backendUrl = import.meta.env.VITE_BACKEND_URL ? import.meta.env.VITE_BACKEND_URL : "http://localhost:8080";

function PlacesRequestForm({OnApiCall}: {OnApiCall: (places: places[]) => void}) {

    const {register, handleSubmit, formState: { errors }} = useForm<Inputs>()
    const onSubmit: SubmitHandler<Inputs> = (data) => {
        fetch(`${backendUrl}/api/v1/places?latitude=${data.latitude}&longitude=${data.longitude}&radius=${data.radius}`)
        .then((response) => response.json())
        .then((data) => {
            OnApiCall(data.places);
            console.log("Fetched places:", data);
        })
        .catch((error) => {
            console.error("Error fetching places:", error);
        });
    }
  return (
    <div className="places-request-form">
      <form className="request-form" onSubmit={handleSubmit(onSubmit)}>
        <input {...register("latitude", {
            required: "Latitude is required",
            min: { value: -90, message: "Latitude must be ≥ -90" },
            max: { value: 90, message: "Latitude must be ≤ 90" }
                })} placeholder="latitude"></input>
        <input {...register("longitude", {
            required: "Longitude must be between -180 and 180",
            min: {value:-180, message: "Longitude must be ≥ -180"},
            max: {value:180, message: "Longitude must be ≤ 180"}
                })} placeholder="longitude"></input>
        <input {...register("radius", {
            required: "Radius must be between 0 and 50000", 
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