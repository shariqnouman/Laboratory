import React, { useEffect, useState } from "react";
import PropTypes from "prop-types";
import { connect } from "react-redux";
import {
  loadAppointments,
  saveAppointment
} from "../../redux/actions/appointmentActions";
import AppointmentForm from "./AppointmentForm";
import { newAppointment } from "../../api/apiUtils";
import { toast } from "react-toastify";

function ManageAppointmentPage({
  appointments,
  loadAppointments,
  saveAppointment,
  history,
  disabled,
  ...props
}) {
  const [appointment, setAppointment] = useState({ ...props.appointment });
  const [errors, setErrors] = useState({});
  const [saving, setSaving] = useState(false);

  useEffect(() => {
    if (appointments.length === 0) {
      loadAppointments().catch(error => {
        alert("Loading failed " + error);
      });
    } else {
      setAppointment({ ...props.appointment });
    }
  }, [props.appointment]);

  function handleChange(event) {
    const { name, value } = event.target;
    setAppointment(prevAppointment => ({
      ...prevAppointment,
      [name]:
        name === "appointmentDate"
          ? new Date(value).toISOString().substring(0, 16)
          : value,
      patient: {
        ...prevAppointment.patient,
        [name]: value
      }
    }));
  }

  function formIsValid() {
    const {
      appointmentDescription,
      appointmentType,
      appointmentDate,
      patient: {
        patientId,
        patientName,
        patientAddress,
        patientEmail,
        patientNumber
      }
    } = appointment;
    const errors = {};

    if (!appointmentDescription)
      errors.appointmentDescription = "Appointment Description is required.";
    if (!appointmentType)
      errors.appointmentType = "Appointment Type is required";
    if (!appointmentDate)
      errors.appointmentDate = "Appointment Date is required";
    if (!patientId) {
      errors.patientId = "Patient Id is required";
    } else if (!String(patientId).match(/^[0-9]+$/)) {
      errors.patientId = "Patient Id must number";
    }
    if (!patientName) errors.patientName = "Patient Name is required";
    if (!patientAddress) errors.patientAddress = "Patient Address is required";
    if (!patientEmail) errors.patientEmail = "Patient Email is required";
    if (!patientNumber) errors.patientNumber = "Patient Number is required";

    setErrors(errors);
    // Form is valid if the errors object still has no properties
    return Object.keys(errors).length === 0;
  }

  function handleSave(event) {
    event.preventDefault();
    if (!formIsValid()) return;
    setSaving(true);
    saveAppointment(appointment)
      .then(() => {
        toast.success("Appointment Booked");
        history.push("/admin/appointment");
      })
      .catch(error => {
        setSaving(false);
        setErrors({ onSave: error.message });
      });
  }

  return (
    <>
      <AppointmentForm
        appointment={appointment}
        errors={errors}
        onChange={handleChange}
        onSave={handleSave}
        saving={saving}
        disabled={disabled}
      />
    </>
  );
}

ManageAppointmentPage.propTypes = {
  appointments: PropTypes.array.isRequired,
  loadAppointments: PropTypes.func.isRequired,
  saveAppointment: PropTypes.func.isRequired,
  appointment: PropTypes.object.isRequired,
  history: PropTypes.object.isRequired
};

export function getAppointment(appointmentslist, appointmentId) {
  return (
    appointmentslist.find(
      appointment => appointment.appointmentId == appointmentId
    ) || null
  );
}

function mapStateToProps(state, ownProps) {
  const appointmentId = ownProps.match.params.appointmentId;
  const disabled = ownProps.location.state.disabled;
  const appointment =
    appointmentId && state.appointments.length > 0
      ? getAppointment(state.appointments, appointmentId)
      : newAppointment;

  return {
    appointments: state.appointments,
    appointment,
    disabled
  };
}

const mapDispatchToProps = {
  loadAppointments,
  saveAppointment
};

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(ManageAppointmentPage);
