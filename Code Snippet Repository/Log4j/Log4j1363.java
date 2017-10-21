    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final SslConfiguration other = (SslConfiguration) obj;
        if (keyStoreConfig == null) {
            if (other.keyStoreConfig != null) {
                return false;
            }
        } else if (!keyStoreConfig.equals(other.keyStoreConfig)) {
            return false;
        }
        if (protocol == null) {
            if (other.protocol != null) {
                return false;
            }
        } else if (!protocol.equals(other.protocol)) {
            return false;
        }
        if (sslContext == null) {
            if (other.sslContext != null) {
                return false;
            }
        } else if (!sslContext.equals(other.sslContext)) {
            return false;
        }
        if (trustStoreConfig == null) {
            if (other.trustStoreConfig != null) {
                return false;
            }
        } else if (!trustStoreConfig.equals(other.trustStoreConfig)) {
            return false;
        }
        return true;
    }
