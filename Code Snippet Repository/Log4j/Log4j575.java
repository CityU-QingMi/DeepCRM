    @BeforeClass
    public static void setupClass() {

		final Configuration config = LoggerContext.getContext().getConfiguration();
		
		if (!DefaultConfiguration.DEFAULT_NAME.equals(config.getName())) {
			System.out.println("Configuration was " + config.getName());
			LoggerContext.getContext().start(new DefaultConfiguration());
		}

        for (int i=0; i < WARMUP; ++i) {
            overhead();
        }
        System.gc();
        final Timer timer = new Timer("Setup", LOOP_CNT);
        timer.start();
        for (int i=0; i < (LOOP_CNT / 150); ++i) {
            overhead();
        }
        timer.stop();
        maxTime = timer.getElapsedNanoTime();
        System.gc();
        System.out.println(timer.toString());
    }
