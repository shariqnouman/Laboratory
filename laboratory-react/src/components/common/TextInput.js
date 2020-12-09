import React from "react";
import PropTypes from "prop-types";

const TextInput = ({ name, label, onChange, value, error, disabled }) => (
  <div className="form-group row">
    <label htmlFor={name} className="col-md-3 offset-1 font-weight-bold">
      {label}
    </label>
    <div className="field col-md-6">
      <input
        type="text"
        name={name}
        className="form-control"
        value={value}
        onChange={onChange}
        disabled={disabled}
      />
      {error && <div className="alert alert-danger">{error}</div>}
    </div>
  </div>
);

TextInput.propTypes = {
  name: PropTypes.string.isRequired,
  label: PropTypes.string.isRequired,
  onChange: PropTypes.func.isRequired,
  placeholder: PropTypes.string,
  value: PropTypes.string,
  error: PropTypes.string
};

export default TextInput;
