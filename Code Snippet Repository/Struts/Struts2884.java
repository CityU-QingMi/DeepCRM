    public void setTrimDirectiveWhitespaces(String value, Node n, ErrorDispatcher err,
                   boolean pagedir)
        throws JasperException {

        if ("true".equalsIgnoreCase(value))
            trimDirectiveWhitespaces = true;
        else if ("false".equalsIgnoreCase(value))
            trimDirectiveWhitespaces = false;
        else {
            if (pagedir)
                err.jspError(n, "jsp.error.page.invalid.trimdirectivewhitespaces");
            else
                err.jspError(n, "jsp.error.tag.invalid.trimdirectivewhitespaces");
        }

        trimDirectiveWhitespacesValue = value;
    }
