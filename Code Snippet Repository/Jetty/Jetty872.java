    protected void header(HttpField field)
    {
        String name = field.getName();
        String value = field.getValue();
        getRequest().setAttribute(name, value);
        if (FCGI.Headers.REQUEST_METHOD.equalsIgnoreCase(name))
            method = value;
        else if (FCGI.Headers.DOCUMENT_URI.equalsIgnoreCase(name))
            path = value;
        else if (FCGI.Headers.QUERY_STRING.equalsIgnoreCase(name))
            query = value;
        else if (FCGI.Headers.SERVER_PROTOCOL.equalsIgnoreCase(name))
            version = value;
        else
            processField(field);
    }
