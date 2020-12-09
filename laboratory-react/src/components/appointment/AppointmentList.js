import React from "react";
import PropTypes from "prop-types";
import { Link } from "react-router-dom";

const AppointmentList = ({ appointments, onDeleteClick }) => (
  <table className="table table-hover">
    <thead className="thead-dark">
      <tr>
        <th>Appointment Id</th>
        <th>Patient Id</th>
        <th style={{ width: "400px" }}>Appointment Description</th>
        <th>Appointment Date</th>
        <th>Appointment Type</th>
        <th />
        <th />
      </tr>
    </thead>
    <tbody>
      {appointments.map(appointment => {
        return (
          <tr key={appointment.appointmentId}>
            <td className="table-active font-weight-bold">
              {appointment.appointmentId}
            </td>
            <td className="">{appointment.patient.patientId}</td>
            <td style={{ width: "400px" }}>
              {appointment.appointmentDescription}
            </td>
            <td>{appointment.appointmentDate.replace("T", " ")}</td>
            <td>{appointment.appointmentType}</td>
            <td>
              <Link
                to={{
                  pathname: "/admin/manage/" + appointment.appointmentId,
                  state: { disabled: true }
                }}
                className="btn btn-dark"
              >
                Edit
              </Link>
            </td>
            <td>
              <button
                onClick={() => onDeleteClick(appointment)}
                className="btn btn-danger"
              >
                Remove
              </button>
            </td>
          </tr>
        );
      })}
    </tbody>
  </table>
);

AppointmentList.propTypes = {
  appointments: PropTypes.array.isRequired,
  onDeleteClick: PropTypes.func.isRequired
};

export default AppointmentList;
