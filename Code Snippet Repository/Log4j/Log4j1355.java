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
        final KeyStoreConfiguration other = (KeyStoreConfiguration) obj;
        if (keyManagerFactoryAlgorithm == null) {
            if (other.keyManagerFactoryAlgorithm != null) {
                return false;
            }
        } else if (!keyManagerFactoryAlgorithm.equals(other.keyManagerFactoryAlgorithm)) {
            return false;
        }
        return true;
    }
