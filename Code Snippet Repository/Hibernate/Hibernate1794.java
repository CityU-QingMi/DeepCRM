	@Override
	public void contributeTypes(TypeContributions typeContributions, ServiceRegistry serviceRegistry) {
		super.contributeTypes( typeContributions, serviceRegistry );

		// account for Oracle's deprecated support for LONGVARBINARY...
		// 		prefer BLOB, unless the user opts out of it
		boolean preferLong = serviceRegistry.getService( ConfigurationService.class ).getSetting(
				PREFER_LONG_RAW,
				StandardConverters.BOOLEAN,
				false
		);

		if ( !preferLong ) {
			typeContributions.contributeType( MaterializedBlobType.INSTANCE, "byte[]", byte[].class.getName() );
			typeContributions.contributeType( WrappedMaterializedBlobType.INSTANCE, "Byte[]", Byte[].class.getName() );
		}
	}
