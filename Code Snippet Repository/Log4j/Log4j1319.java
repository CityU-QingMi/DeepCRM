    public static DatagramSocketManager getSocketManager(final String host, final int port,
            final Layout<? extends Serializable> layout, final int bufferSize) {
        if (Strings.isEmpty(host)) {
            throw new IllegalArgumentException("A host name is required");
        }
        if (port <= 0) {
            throw new IllegalArgumentException("A port value is required");
        }
        return (DatagramSocketManager) getManager("UDP:" + host + ':' + port,
                new FactoryData(host, port, layout, bufferSize), FACTORY);
    }
