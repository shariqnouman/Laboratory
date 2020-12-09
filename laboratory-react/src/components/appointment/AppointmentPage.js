import PropTypes from "prop-types";
import React from "react";
import { connect } from "react-redux";
import { Redirect } from "react-router-dom";
import { bindActionCreators } from "redux";
import "../../css/appointment.css";
import * as appointmentActions from "../../redux/actions/appointmentActions";
import AppointmentList from "./AppointmentList";
import { toast } from "react-toastify";
import { Link } from "react-router-dom";
import { confirmAlert } from "react-confirm-alert";
import "react-confirm-alert/src/react-confirm-alert.css";

class AppointmentPage extends React.Component {
  state = {
    redirect: false
  };

  componentDidMount() {
    const { appointments, actions } = this.props;
    if (appointments.length === 0) {
      actions.loadAppointments().catch(error => {
        alert("Loading failed " + error);
      });
    }
  }

  submit = appointment => {
    confirmAlert({
      customUI: ({ onClose }) => {
        return (
          <div className="border rounded p-5 bg-dark">
            <h1 className="font-weight-bold text-white">Confirm to delete</h1>
            <h4 className="mt-3 text-white">
              Are you sure to delete this appointment ?
            </h4>
            <div className="row">
              <div className="m-4 col-md-6 offset-3">
                <button
                  onClick={onClose}
                  className="btn btn-danger font-weight-bold"
                >
                  Cancle
                </button>
              </div>
              <div className="m-4">
                <button
                  className="btn btn-success font-weight-bold"
                  onClick={() => {
                    this.handleDeleteAppointment(appointment);
                    onClose();
                  }}
                >
                  Delete!
                </button>
              </div>
            </div>
          </div>
        );
      }
    });
  };

  handleDeleteAppointment = async appointment => {
    toast.success("Appointment deleted");
    this.props.actions.deleteAppointment(appointment).catch(error => {
      toast.error("Delete failed. " + error.message, { autoClose: false });
    });
  };

  render() {
    return (
      <>
        {this.state.redirect && (
          <Redirect
            to={{
              pathname: "/admin/manage",
              state: { disabled: false }
            }}
          />
        )}
        <h2 className="text-center text-white bg-success rounded-pill font-weight-bold p-2 mb-3 col-md-6 offset-4">
          Appointments
        </h2>
        <div className="row">
          <div className="col-md-2">
            <button
              className="btn book-btn"
              onClick={() => this.setState({ redirect: true })}
            >
              Book Appointment
            </button>
            <Link to="/admin" className="btn book-btn">
              Back
            </Link>
          </div>
          <div className="col-md-10 table-responsive">
            <AppointmentList
              onDeleteClick={this.submit}
              appointments={this.props.appointments}
            />
          </div>
        </div>
      </>
    );
  }
}

AppointmentPage.propTypes = {
  appointments: PropTypes.array.isRequired,
  actions: PropTypes.object.isRequired
};

function mapStateToProps(state) {
  return {
    appointments: state.appointments
  };
}

function mapDispatchToProps(dispatch) {
  return {
    actions: bindActionCreators(appointmentActions, dispatch)
  };
}

export default connect(mapStateToProps, mapDispatchToProps)(AppointmentPage);
