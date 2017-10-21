	private void configure(StandardServiceRegistryBuilder ssrBuilder) {

		applyJdbcConnectionProperties( ssrBuilder );
		applyTransactionProperties( ssrBuilder );

		// flush before completion validation
		if ( "true".equals( configurationValues.get( Environment.FLUSH_BEFORE_COMPLETION ) ) ) {
			ssrBuilder.applySetting( Environment.FLUSH_BEFORE_COMPLETION, "false" );
			LOG.definingFlushBeforeCompletionIgnoredInHem( Environment.FLUSH_BEFORE_COMPLETION );
		}

//		final StrategySelector strategySelector = ssrBuilder.getBootstrapServiceRegistry().getService( StrategySelector.class );
//		final Object interceptorSetting = configurationValues.remove( AvailableSettings.SESSION_INTERCEPTOR );
//		if ( interceptorSetting != null ) {
//			settings.setSessionInterceptorClass(
//					loadSessionInterceptorClass( interceptorSetting, strategySelector )
//			);
//		}
	}
