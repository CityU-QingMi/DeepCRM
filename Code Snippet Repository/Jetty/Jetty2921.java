    private int findServerPort()
    {
        MetaData.Request metadata = _metaData;
        // Return host from header field
        HttpField host = metadata==null?null:metadata.getFields().getField(HttpHeader.HOST);
        if (host!=null)
        {
            // TODO is this needed now?
            HostPortHttpField authority = (host instanceof HostPortHttpField)
                ?((HostPortHttpField)host)
                :new HostPortHttpField(host.getValue());
            metadata.getURI().setAuthority(authority.getHost(),authority.getPort());
            return authority.getPort();
        }

        // Return host from connection
        if (_channel != null)
            return getLocalPort();

        return -1;
    }
