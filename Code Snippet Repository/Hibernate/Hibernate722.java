		@SuppressWarnings("")
		private static Class<? extends Interceptor> determineStatelessInterceptorClass(
				Map configurationSettings,
				StrategySelector strategySelector) {
			Object setting = configurationSettings.get( SESSION_SCOPED_INTERCEPTOR );
			if ( setting == null ) {
				// try the legacy (deprecated) JPA name
				setting = configurationSettings.get( org.hibernate.jpa.AvailableSettings.SESSION_INTERCEPTOR );
				if ( setting != null ) {
					DeprecationLogger.DEPRECATION_LOGGER.deprecatedSetting(
							org.hibernate.jpa.AvailableSettings.SESSION_INTERCEPTOR,
							SESSION_SCOPED_INTERCEPTOR
					);
				}
			}

			if ( setting == null ) {
				return null;
			}
			else if ( setting instanceof Class ) {
				return (Class<? extends Interceptor>) setting;
			}
			else {
				return strategySelector.selectStrategyImplementor(
						Interceptor.class,
						setting.toString()
				);
			}

		}
