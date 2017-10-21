    public void initialize() {

        if (!initialized) {
            LOGGER.debug("Initializing triggering policy {}", triggeringPolicy);
            initialized = true;
            triggeringPolicy.initialize(this);
            if (triggeringPolicy instanceof LifeCycle) {
                ((LifeCycle) triggeringPolicy).start();
            }
        }
    }
