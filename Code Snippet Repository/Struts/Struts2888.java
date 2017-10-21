    public void setSession(String value, Node n, ErrorDispatcher err)
        throws JasperException {

        if ("true".equalsIgnoreCase(value))
            isSession = true;
        else if ("false".equalsIgnoreCase(value))
            isSession = false;
        else
            err.jspError(n, "jsp.error.page.invalid.session");

        session = value;
    }
