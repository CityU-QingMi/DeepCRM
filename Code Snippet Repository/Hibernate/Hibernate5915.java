	@Test
	@SuppressWarnings({ "" })
	public void testBuildingMetamodelWithParameterizedCollection() {
		Metadata metadata = new MetadataSources()
				.addAnnotatedClass( WithGenericCollection.class )
				.buildMetadata();
		SessionFactoryImplementor sfi = (SessionFactoryImplementor) metadata.buildSessionFactory();
		MetamodelImpl metamodel = new MetamodelImpl( sfi );
		metamodel.initialize( (MetadataImplementor) metadata, JpaMetaModelPopulationSetting.IGNORE_UNSUPPORTED );
		sfi.close();
	}
