import * as types from "./actionTypes";
import * as appointmentApi from "../../api/appointmentApi";

export function loadAppointmentSuccess(appointments) {
  return { type: types.LOAD_APPOINMENT_SUCCESS, appointments };
}

export function createAppointmentSuccess(appointment) {
  return { type: types.CREATE_APPOINTMENT_SUCCESS, appointment };
}

export function updateAppointmentSuccess(appointment) {
  return { type: types.UPDATE_APPOINTMENT_SUCCESS, appointment };
}

export function deleteAppointmentSuccess(appointment) {
  return { type: types.DELETE_APPOINTMENT_SUCCESS, appointment };
}

export function loadAppointments() {
  return function(dispatch) {
    return appointmentApi
      .getAppointments()
      .then(appointments => {
        dispatch(loadAppointmentSuccess(appointments));
      })
      .catch(error => {
        throw error;
      });
  };
}

export function saveAppointment(appointment) {
  return function(dispatch, getState) {
    return appointmentApi
      .saveAppointment(appointment)
      .then(savedAppointment => {
        appointment.appointmentId
          ? dispatch(updateAppointmentSuccess(savedAppointment))
          : dispatch(createAppointmentSuccess(savedAppointment));
      })
      .catch(error => {
        throw error;
      });
  };
}

export function deleteAppointment(appointment) {
  return function(dispatch) {
    dispatch(deleteAppointmentSuccess(appointment));
    return appointmentApi.deleteAppointment(appointment.appointmentId);
  };
}
