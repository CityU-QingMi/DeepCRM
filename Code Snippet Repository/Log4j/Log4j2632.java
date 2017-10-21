    @Override
    public Statement apply(final Statement base, final Description description) {
        return new Statement() {

            @Override
            public void evaluate() throws Throwable {
                final String oldValue = System.getProperty(name);
                try {
                    port = AvailablePortFinder.getNextAvailable();
                    System.setProperty(name, Integer.toString(port));
                    base.evaluate();
                } finally {
                    // Restore if previously set
                    if (oldValue != null) {
                        System.setProperty(name, oldValue);
                    }
                }
            }
        };
    }
