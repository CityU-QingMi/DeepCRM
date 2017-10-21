	public ComponentAuditedPropertiesReader(
			ModificationStore defaultStore,
			PersistentPropertiesSource persistentPropertiesSource,
			AuditedPropertiesHolder auditedPropertiesHolder,
			GlobalConfiguration globalCfg, ReflectionManager reflectionManager,
			String propertyNamePrefix) {
		super(
				defaultStore, persistentPropertiesSource, auditedPropertiesHolder,
				globalCfg, reflectionManager, propertyNamePrefix
		);
	}
