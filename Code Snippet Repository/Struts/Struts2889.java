    public void setAutoFlush(String value, Node n, ErrorDispatcher err)
        throws JasperException {

        if ("true".equalsIgnoreCase(value))
            isAutoFlush = true;
        else if ("false".equalsIgnoreCase(value))
            isAutoFlush = false;
        else
            err.jspError(n, "jsp.error.autoFlush.invalid");

        autoFlush = value;
    }
