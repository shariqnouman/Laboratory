import { handleResponse, handleError } from "./apiUtils";

const baseUrl = "http://localhost:8080/api/v1/appointment/";

export function getAppointments() {
  return fetch(baseUrl)
    .then(handleResponse)
    .catch(handleError);
}

export function saveAppointment(appointment) {
  return fetch(baseUrl + (appointment.appointmentId || ""), {
    method: appointment.appointmentId ? "PUT" : "POST", // POST for create, PUT to update when id already exists.
    headers: { "content-type": "application/json" },
    body: JSON.stringify(appointment)
  })
    .then(handleResponse)
    .catch(handleError);
}

export function deleteAppointment(appointmentId) {
  return fetch(baseUrl + appointmentId, { method: "DELETE" })
    .then()
    .catch(handleError);
}
