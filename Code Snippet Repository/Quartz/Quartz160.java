    public void setRMICreateRegistryStrategy(String rmiCreateRegistryStrategy) {
        if (rmiCreateRegistryStrategy == null
                || rmiCreateRegistryStrategy.trim().length() == 0) {
            rmiCreateRegistryStrategy = CREATE_REGISTRY_NEVER;
        } else if (rmiCreateRegistryStrategy.equalsIgnoreCase("true")) {
            rmiCreateRegistryStrategy = CREATE_REGISTRY_AS_NEEDED;
        } else if (rmiCreateRegistryStrategy.equalsIgnoreCase("false")) {
            rmiCreateRegistryStrategy = CREATE_REGISTRY_NEVER;
        } else if (rmiCreateRegistryStrategy.equalsIgnoreCase(CREATE_REGISTRY_ALWAYS)) {
            rmiCreateRegistryStrategy = CREATE_REGISTRY_ALWAYS;
        } else if (rmiCreateRegistryStrategy.equalsIgnoreCase(CREATE_REGISTRY_AS_NEEDED)) {
            rmiCreateRegistryStrategy = CREATE_REGISTRY_AS_NEEDED;
        } else if (rmiCreateRegistryStrategy.equalsIgnoreCase(CREATE_REGISTRY_NEVER)) {
            rmiCreateRegistryStrategy = CREATE_REGISTRY_NEVER;
        } else {
            throw new IllegalArgumentException(
                    "Faild to set RMICreateRegistryStrategy - strategy unknown: '"
                            + rmiCreateRegistryStrategy + "'");
        }

        this.rmiCreateRegistryStrategy = rmiCreateRegistryStrategy;
    }
