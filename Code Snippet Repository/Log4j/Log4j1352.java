    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!super.equals(obj)) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final AbstractKeyStoreConfiguration other = (AbstractKeyStoreConfiguration) obj;
        if (keyStore == null) {
            if (other.keyStore != null) {
                return false;
            }
        } else if (!keyStore.equals(other.keyStore)) {
            return false;
        }
        if (keyStoreType == null) {
            if (other.keyStoreType != null) {
                return false;
            }
        } else if (!keyStoreType.equals(other.keyStoreType)) {
            return false;
        }
        return true;
    }
