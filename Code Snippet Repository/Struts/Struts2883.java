    public void setDeferredSyntaxAllowedAsLiteral(String value, Node n, ErrorDispatcher err,
                   boolean pagedir)
        throws JasperException {

        if ("true".equalsIgnoreCase(value))
            deferredSyntaxAllowedAsLiteral = true;
        else if ("false".equalsIgnoreCase(value))
            deferredSyntaxAllowedAsLiteral = false;
        else {
            if (pagedir)
                err.jspError(n, "jsp.error.page.invalid.deferredsyntaxallowedasliteral");
            else
                err.jspError(n, "jsp.error.tag.invalid.deferredsyntaxallowedasliteral");
        }

        deferredSyntaxAllowedAsLiteralValue = value;
    }
