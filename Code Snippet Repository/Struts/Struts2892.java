    public void setIsELIgnored(String value, Node n, ErrorDispatcher err,
                   boolean pagedir)
        throws JasperException {

        if ("true".equalsIgnoreCase(value))
            isELIgnored = true;
        else if ("false".equalsIgnoreCase(value))
            isELIgnored = false;
        else {
            if (pagedir)
                err.jspError(n, "jsp.error.page.invalid.iselignored");
            else
                err.jspError(n, "jsp.error.tag.invalid.iselignored");
        }

        isELIgnoredValue = value;
    }
