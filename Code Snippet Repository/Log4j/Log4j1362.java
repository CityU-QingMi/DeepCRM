    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((keyStoreConfig == null) ? 0 : keyStoreConfig.hashCode());
        result = prime * result + ((protocol == null) ? 0 : protocol.hashCode());
        result = prime * result + ((sslContext == null) ? 0 : sslContext.hashCode());
        result = prime * result + ((trustStoreConfig == null) ? 0 : trustStoreConfig.hashCode());
        return result;
    }
