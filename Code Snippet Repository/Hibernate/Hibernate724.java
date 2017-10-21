		@Deprecated
		private PhysicalConnectionHandlingMode interpretConnectionHandlingMode(
				ConnectionAcquisitionMode specifiedAcquisitionMode,
				ConnectionReleaseMode specifiedReleaseMode,
				Map configurationSettings,
				TransactionCoordinatorBuilder transactionCoordinatorBuilder) {
			DeprecationLogger.DEPRECATION_LOGGER.logUseOfDeprecatedConnectionHandlingSettings();

			final ConnectionAcquisitionMode effectiveAcquisitionMode = specifiedAcquisitionMode == null
					? ConnectionAcquisitionMode.AS_NEEDED
					: specifiedAcquisitionMode;

			final ConnectionReleaseMode effectiveReleaseMode;
			if ( specifiedReleaseMode == null ) {
				// check the actual setting.  If we get in here it *should* be "auto" or null
				final String releaseModeName = ConfigurationHelper.getString( RELEASE_CONNECTIONS, configurationSettings, "auto" );
				assert "auto".equalsIgnoreCase( releaseModeName );
				// nothing was specified (or someone happened to configure the "magic" value)
				if ( effectiveAcquisitionMode == ConnectionAcquisitionMode.IMMEDIATELY ) {
					effectiveReleaseMode = ConnectionReleaseMode.ON_CLOSE;
				}
				else {
					effectiveReleaseMode = transactionCoordinatorBuilder.getDefaultConnectionReleaseMode();
				}
			}
			else {
				effectiveReleaseMode = specifiedReleaseMode;
			}

			return PhysicalConnectionHandlingMode.interpret( effectiveAcquisitionMode, effectiveReleaseMode );
		}
