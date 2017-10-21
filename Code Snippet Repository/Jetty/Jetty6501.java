    @Override
    public String getProtocolVersion()
    {
        String version = getHeader("Sec-WebSocket-Version");
        if (version == null)
        {
            return "13"; // Default
        }
        return version;
    }
