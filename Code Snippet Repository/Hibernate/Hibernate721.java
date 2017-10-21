		private static Interceptor determineInterceptor(Map configurationSettings, StrategySelector strategySelector) {
			Object setting = configurationSettings.get( INTERCEPTOR );
			if ( setting == null ) {
				// try the legacy (deprecated) JPA name
				setting = configurationSettings.get( org.hibernate.jpa.AvailableSettings.INTERCEPTOR );
				if ( setting != null ) {
					DeprecationLogger.DEPRECATION_LOGGER.deprecatedSetting(
							org.hibernate.jpa.AvailableSettings.INTERCEPTOR,
							INTERCEPTOR
					);
				}
			}

			return strategySelector.resolveStrategy(
					Interceptor.class,
					setting
			);
		}
