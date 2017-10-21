	public static TargetDescriptor buildTargetDescriptor(
			EnumSet<TargetType> targetTypes,
			String outputFile,
			ServiceRegistry serviceRegistry) {
		final ScriptTargetOutput scriptTarget;
		if ( targetTypes.contains( TargetType.SCRIPT ) ) {
			if ( outputFile == null ) {
				throw new SchemaManagementException( "Writing to script was requested, but no script file was specified" );
			}
			scriptTarget = Helper.interpretScriptTargetSetting(
					outputFile,
					serviceRegistry.getService( ClassLoaderService.class ),
					(String) serviceRegistry.getService( ConfigurationService.class ).getSettings().get( AvailableSettings.HBM2DDL_CHARSET_NAME )
			);
		}
		else {
			scriptTarget = null;
		}

		return new TargetDescriptorImpl( targetTypes, scriptTarget );
	}
