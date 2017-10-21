    @PluginFactory
    public static Agent createAgent(@PluginAttribute("host") String host,
            @PluginAttribute("port") final String port) {
        if (host == null) {
            host = DEFAULT_HOST;
        }

        int portNum;
        try {
            portNum = Integers.parseInt(port, DEFAULT_PORT);
        } catch (final Exception ex) {
            LOGGER.error("Error parsing port number " + port, ex);
            return null;
        }
        return new Agent(host, portNum);
    }
