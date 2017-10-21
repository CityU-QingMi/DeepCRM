    public void setLanguage(String value, Node n, ErrorDispatcher err,
                boolean pagedir)
        throws JasperException {

        if (!"java".equalsIgnoreCase(value)) {
            if (pagedir)
                err.jspError(n, "jsp.error.page.language.nonjava");
            else
                err.jspError(n, "jsp.error.tag.language.nonjava");
        }

        language = value;
    }
