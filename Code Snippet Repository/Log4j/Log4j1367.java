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
        final TrustStoreConfiguration other = (TrustStoreConfiguration) obj;
        if (trustManagerFactoryAlgorithm == null) {
            if (other.trustManagerFactoryAlgorithm != null) {
                return false;
            }
        } else if (!trustManagerFactoryAlgorithm.equals(other.trustManagerFactoryAlgorithm)) {
            return false;
        }
        return true;
    }
