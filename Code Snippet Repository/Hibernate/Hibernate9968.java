	@Override
	@SuppressWarnings("")
	public void mapToEntityFromMap(
			EnversService enversService,
			Object obj,
			Map data,
			Object primaryKey,
			AuditReaderImplementor versionsReader,
			Number revision) {
		Object mapProxy = MapProxyTool.newInstanceOfBeanProxyForMap(
				generateClassName( data, dynamicComponentData.getBeanName() ),
				(Map) obj,
				properties.keySet(),
				enversService.getClassLoaderService()
		);
		for ( PropertyMapper mapper : properties.values() ) {
			mapper.mapToEntityFromMap( enversService, mapProxy, data, primaryKey, versionsReader, revision );
		}
	}
