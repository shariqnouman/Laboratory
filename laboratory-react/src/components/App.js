import React from "react";
import { Route, Switch } from "react-router-dom";
import HomePage from "./home/HomePage";
import AboutPage from "./about/AboutPage";
import AdminPage from "./admin/AdminPage";
import Header from "./common/Header";
import PageNotFound from "./PageNotFound";
import AppointmentPage from "./appointment/AppointmentPage";
import ReportPage from "./report/ReportPage";
import ManageAppointmentPage from "./appointment/ManageAppointmentPage";
import { ToastContainer } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";

//background-repeat: no-repeat;
//background-size: cover;
function App() {
  return (
    <div className="container-fluid" style={{ fontFamily: "Lucida Console" }}>
      <Header />
      <Switch>
        <Route exact path="/" component={HomePage} />
        <Route path="/about" component={AboutPage} />
        <Route exact path="/admin" component={AdminPage} />
        <Route path="/admin/appointment" component={AppointmentPage} />
        <Route
          path="/admin/manage/:appointmentId"
          component={ManageAppointmentPage}
        />
        <Route exact path="/admin/manage" component={ManageAppointmentPage} />
        <Route path="/admin/report" component={ReportPage} />
        <Route component={PageNotFound} />
      </Switch>
      <ToastContainer autoClose={3000} hideProgressBar />
    </div>
  );
}

export default App;
