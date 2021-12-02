import React from "react";
import classes from "./styles.module.css";

const Button = (props) => {
    const { action, children, disabled = false }= props;
    return (
        <button
            onClick={action}
            disabled={disabled}>
            {children}
        </button>
    );
}

export default Button;