    @Override
    public String getProtocolVersion()
    {
        String version = request.getHeader(WebSocketConstants.SEC_WEBSOCKET_VERSION);
        if(version == null) 
        {
            return Integer.toString(WebSocketConstants.SPEC_VERSION);
        }
        return version;
    }
