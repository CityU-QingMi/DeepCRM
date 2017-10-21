	@Override
	public void configure(Map configurationValues) {
		log.usingHibernateBuiltInConnectionPool();

		pool = buildPool( configurationValues );

		final long validationInterval = ConfigurationHelper.getLong( VALIDATION_INTERVAL, configurationValues, 30 );
		executorService = Executors.newSingleThreadScheduledExecutor();
		executorService.scheduleWithFixedDelay(
				new Runnable() {
					private boolean primed;
					@Override
					public void run() {
						pool.validate();
					}
				},
				validationInterval,
				validationInterval,
				TimeUnit.SECONDS
		);
	}
