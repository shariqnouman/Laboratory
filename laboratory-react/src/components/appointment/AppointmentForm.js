import React from "react";
import PropTypes from "prop-types";
import TextInput from "../common/TextInput";
import { Link } from "react-router-dom";

const AppointmentForm = ({
  appointment,
  onSave,
  onChange,
  disabled,
  saving = false,
  errors = {}
}) => {
  console.log(appointment);
  return (
    <>
      <div className="row mb-5">
        <div className="col-md-6 offset-2 ">
          <h2
            className="text-center text-white bg-success rounded-pill font-weight-bold"
            style={{ padding: "15px" }}
          >
            {appointment.appointmentId ? "Edit" : "Book"} Appointment
          </h2>
        </div>
        <div className="col=md-3 offset-1">
          <Link
            to="/admin/appointment"
            className="btn book-btn"
            style={{ marginTop: 0 }}
          >
            Back
          </Link>
        </div>
      </div>
      <form onSubmit={onSave}>
        {errors.onSave && (
          <div className="alert alert-danger" role="alert">
            {errors.onSave}
          </div>
        )}
        <TextInput
          name="appointmentDescription"
          label="Appointment Description"
          value={appointment.appointmentDescription}
          onChange={onChange}
          error={errors.appointmentDescription}
        />

        <TextInput
          name="appointmentType"
          label="Appointment Type"
          value={appointment.appointmentType}
          onChange={onChange}
          error={errors.appointmentType}
        />

        <div className="form-group row">
          <label className="col-md-3 offset-1 font-weight-bold">
            Appointment Date
          </label>
          <div className="field col-md-6">
            <input
              type="datetime-local"
              name="appointmentDate"
              value={appointment.appointmentDate}
              onChange={onChange}
              error={errors.appointmentDate}
            ></input>
          </div>
        </div>

        <TextInput
          name="patientId"
          label="Patient Id"
          value={appointment.patient.patientId}
          onChange={onChange}
          error={errors.patientId}
          disabled={disabled}
        />

        <TextInput
          name="patientName"
          label="Patient Name"
          value={appointment.patient.patientName}
          onChange={onChange}
          error={errors.patientName}
        />

        <TextInput
          name="patientAddress"
          label="patient Address"
          value={appointment.patient.patientAddress}
          onChange={onChange}
          error={errors.patientAddress}
        />

        <TextInput
          name="patientEmail"
          label="Patient Email"
          value={appointment.patient.patientEmail}
          onChange={onChange}
          error={errors.patientEmail}
        />

        <TextInput
          name="patientNumber"
          label="Patient Number"
          value={appointment.patient.patientNumber}
          onChange={onChange}
          error={errors.patientNumber}
        />

        <div className="text-center">
          <button
            type="submit"
            disabled={saving}
            className="btn book-btn px-5 m-0"
          >
            {saving ? "Saving..." : "Save"}
          </button>
        </div>
      </form>
    </>
  );
};

AppointmentForm.propTypes = {
  appointment: PropTypes.object.isRequired,
  errors: PropTypes.object,
  onSave: PropTypes.func.isRequired,
  onChange: PropTypes.func.isRequired,
  saving: PropTypes.bool
};

export default AppointmentForm;
