		private PhysicalConnectionHandlingMode interpretConnectionHandlingMode(
				Map configurationSettings,
				StandardServiceRegistry serviceRegistry) {
			final PhysicalConnectionHandlingMode specifiedHandlingMode = PhysicalConnectionHandlingMode.interpret(
					configurationSettings.get( CONNECTION_HANDLING )
			);

			if ( specifiedHandlingMode != null ) {
				return specifiedHandlingMode;
			}


			final TransactionCoordinatorBuilder transactionCoordinatorBuilder = serviceRegistry.getService( TransactionCoordinatorBuilder.class );

			// see if the deprecated ConnectionAcquisitionMode/ConnectionReleaseMode were used..
			final ConnectionAcquisitionMode specifiedAcquisitionMode = ConnectionAcquisitionMode.interpret(
					configurationSettings.get( ACQUIRE_CONNECTIONS )
			);
			final ConnectionReleaseMode specifiedReleaseMode = ConnectionReleaseMode.interpret(
					configurationSettings.get( RELEASE_CONNECTIONS )
			);
			if ( specifiedAcquisitionMode != null || specifiedReleaseMode != null ) {
				return interpretConnectionHandlingMode( specifiedAcquisitionMode, specifiedReleaseMode, configurationSettings, transactionCoordinatorBuilder );
			}

			return transactionCoordinatorBuilder.getDefaultConnectionHandlingMode();
		}
