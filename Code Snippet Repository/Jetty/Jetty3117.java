    private String getXForwardedFor(Request request)
    {
        // Get the right most XForwarded-For for value.
        // This is the value from the closest proxy and the only one that
        // can be trusted.
        String forwarded_for = null;
        HttpFields httpFields = request.getHttpFields();
        for (HttpField field : httpFields)
            if (_forwardedHeader.equalsIgnoreCase(field.getName()))
                forwarded_for = field.getValue();
        
        if (forwarded_for==null || forwarded_for.isEmpty())
            return null;
        
        int comma = forwarded_for.lastIndexOf(',');
        return (comma>=0)?forwarded_for.substring(comma+1).trim():forwarded_for;
    }
