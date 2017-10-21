	public ClassAuditingData getAuditData() {
		if ( pc.getClassName() == null ) {
			return auditData;
		}

		try {
			final XClass xclass = reflectionManager.classForName( pc.getClassName() );

			final ModificationStore defaultStore = getDefaultAudited( xclass );
			if ( defaultStore != null ) {
				auditData.setDefaultAudited( true );
			}

			new AuditedPropertiesReader(
					defaultStore,
					new PersistentClassPropertiesSource( xclass ),
					auditData,
					globalCfg,
					reflectionManager,
					""
			).read();

			addAuditTable( xclass );
			addAuditSecondaryTables( xclass );
		}
		catch (ClassLoadingException e) {
			throw new MappingException( e );
		}

		return auditData;
	}
