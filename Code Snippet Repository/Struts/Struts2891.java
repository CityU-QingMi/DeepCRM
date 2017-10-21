    public void setIsErrorPage(String value, Node n, ErrorDispatcher err)
        throws JasperException {

        if ("true".equalsIgnoreCase(value))
            isErrorPage = true;
        else if ("false".equalsIgnoreCase(value))
            isErrorPage = false;
        else
            err.jspError(n, "jsp.error.page.invalid.iserrorpage");

        isErrorPageValue = value;
    }
