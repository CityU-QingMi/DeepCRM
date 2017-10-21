	private CacheCommandFactory getCacheCommandFactory() {
		final GlobalComponentRegistry globalCr = manager.getGlobalComponentRegistry();

		final Map<Byte, ModuleCommandFactory> factories =
				(Map<Byte, ModuleCommandFactory>) globalCr.getComponent( "org.infinispan.modules.command.factories" );

		for ( ModuleCommandFactory factory : factories.values() ) {
			if ( factory instanceof CacheCommandFactory ) {
				return (CacheCommandFactory) factory;
			}
		}

		throw log.cannotInstallCommandFactory();
	}
