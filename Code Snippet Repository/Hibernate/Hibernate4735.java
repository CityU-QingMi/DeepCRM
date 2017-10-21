	private StatisticsImplementor initiateServiceInternal(
			SessionFactoryImplementor sessionFactory,
			Object configValue,
			ServiceRegistryImplementor registry) {

		StatisticsFactory statisticsFactory;
		if ( configValue == null ) {
			statisticsFactory = DEFAULT_STATS_BUILDER;
		}
		else if ( StatisticsFactory.class.isInstance( configValue ) ) {
			statisticsFactory = (StatisticsFactory) configValue;
		}
		else {
			// assume it names the factory class
			final ClassLoaderService classLoaderService = registry.getService( ClassLoaderService.class );
			try {
				statisticsFactory = (StatisticsFactory) classLoaderService.classForName( configValue.toString() ).newInstance();
			}
			catch (HibernateException e) {
				throw e;
			}
			catch (Exception e) {
				throw new HibernateException(
						"Unable to instantiate specified StatisticsFactory implementation [" + configValue.toString() + "]",
						e
				);
			}
		}

		StatisticsImplementor statistics = statisticsFactory.buildStatistics( sessionFactory );
		final boolean enabled = sessionFactory.getSettings().isStatisticsEnabled();
		statistics.setStatisticsEnabled( enabled );
		LOG.debugf( "Statistics initialized [enabled=%s]", enabled );
		return statistics;
	}
