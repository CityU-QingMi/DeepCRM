    private void initializeCleanupMonitoring()
    {
        callerInfo = new NullPointerException().getStackTrace()[2];

        Runnable warning = new Runnable()
        {

            public void run()
            {
                maybeWarnAboutCleanUp();
            }

        };

        cleanupWarning = new Thread( warning );

        Runtime.getRuntime().addShutdownHook( cleanupWarning );
    }
