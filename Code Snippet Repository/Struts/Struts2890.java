    public void setIsThreadSafe(String value, Node n, ErrorDispatcher err)
        throws JasperException {

        if ("true".equalsIgnoreCase(value))
            isThreadSafe = true;
        else if ("false".equalsIgnoreCase(value))
            isThreadSafe = false;
        else
            err.jspError(n, "jsp.error.page.invalid.isthreadsafe");

        isThreadSafeValue = value;
    }
