    private String getForwarded(Request request)
    {
        // Get the right most Forwarded for value.
        // This is the value from the closest proxy and the only one that
        // can be trusted.
        RFC7239 rfc7239 = new RFC7239();
        HttpFields httpFields = request.getHttpFields();
        for (HttpField field : httpFields)
            if (_forwardedHeader.equalsIgnoreCase(field.getName()))
                rfc7239.addValue(field.getValue());
        
        if (rfc7239.getFor()!=null)
            return new HostPortHttpField(rfc7239.getFor()).getHost();
        
        return null;
    }
