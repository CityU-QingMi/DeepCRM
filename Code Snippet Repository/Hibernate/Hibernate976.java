	public static void processTypeDefinition(
			HbmLocalMetadataBuildingContext context,
			JaxbHbmTypeDefinitionType typeDefinitionBinding) {
		final ClassLoaderService cls = context.getBuildingOptions().getServiceRegistry().getService( ClassLoaderService.class );

		final TypeDefinition definition = new TypeDefinition(
				typeDefinitionBinding.getName(),
				cls.classForName( typeDefinitionBinding.getClazz() ),
				null,
				ConfigParameterHelper.extractConfigParameters( typeDefinitionBinding )
		);

		log.debugf(
				"Processed type-definition : %s -> %s",
				definition.getName(),
				definition.getTypeImplementorClass().getName()
		);

		context.getMetadataCollector().addTypeDefinition( definition );
	}
