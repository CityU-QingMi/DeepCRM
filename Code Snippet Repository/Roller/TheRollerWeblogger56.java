    public static void setupWeblogger() throws Exception {

        if (!WebloggerFactory.isBootstrapped()) {

            // do core services preparation
            WebloggerStartup.prepare();

            // do application bootstrapping
            WebloggerFactory.bootstrap();

            // always initialize the properties manager and flush
            WebloggerFactory.getWeblogger().initialize();

            // Reset data for local tests see
            // docs/testing/roller-junit.properties for howto
            Boolean local = WebloggerConfig.getBooleanProperty(
                    "junit.testdata.reset", false);

            if (local) {

                System.out
                        .println("Reseting tables for local tests: junit.testdata.reset="
                                + local);

                try {
                    clearTestData();
                } catch (Exception e) {
                    System.out.println("Error reseting tables : "
                            + e.getMessage());
                }
            }

        }
    }
