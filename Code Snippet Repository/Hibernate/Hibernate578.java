	public MetadataSources(ServiceRegistry serviceRegistry) {
		// service registry really should be either BootstrapServiceRegistry or StandardServiceRegistry type...
		if ( ! isExpectedServiceRegistryType( serviceRegistry ) ) {
			LOG.debugf(
					"Unexpected ServiceRegistry type [%s] encountered during building of MetadataSources; may cause " +
							"problems later attempting to construct MetadataBuilder",
					serviceRegistry.getClass().getName()
			);
		}
		this.serviceRegistry = serviceRegistry;
		this.xmlMappingBinderAccess = new XmlMappingBinderAccess( serviceRegistry );
	}
