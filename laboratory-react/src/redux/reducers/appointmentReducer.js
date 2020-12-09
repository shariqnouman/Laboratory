import * as types from "../actions/actionTypes";
import { initialState } from "./initialState";

export default function appointmentReducer(
  state = initialState.appointments,
  action
) {
  switch (action.type) {
    case types.LOAD_APPOINMENT_SUCCESS:
      return action.appointments;
    case types.CREATE_APPOINTMENT_SUCCESS:
      return [...state, { ...action.appointment }];
    case types.DELETE_APPOINTMENT_SUCCESS:
      return state.filter(
        appointment =>
          appointment.appointmentId !== action.appointment.appointmentId
      );
    case types.UPDATE_APPOINTMENT_SUCCESS:
      return state.map(appointment =>
        appointment.appointmentId === action.appointment.appointmentId
          ? action.appointment
          : appointment
      );
    default:
      return state;
  }
}
