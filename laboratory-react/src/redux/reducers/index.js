import { combineReducers } from "redux";
import appointments from "./appointmentReducer";

const rootReducer = combineReducers({
  appointments
});

export default rootReducer;
