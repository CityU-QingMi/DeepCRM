	@Deployment
	public static WebArchive buildDeployment() {
		WebArchive war = ShrinkWrap.create( WebArchive.class )
				.setManifest( "org/hibernate/test/wf/ddl/manifest.mf" )
				.addClass( WildFlyDdlEntity.class )
//				.addAsManifestResource( EmptyAsset.INSTANCE, "beans.xml")
				.addAsWebInfResource( "jboss-deployment-structure.xml" ) //Add as "web-inf" resource on Web archives
				.addAsResource( new StringAsset( persistenceXml().exportAsString() ), PERSISTENCE_XML_RESOURCE_NAME )
				.addAsResource( "org/hibernate/test/wf/ddl/log4j.properties", "log4j.properties" );
		System.out.println( war.toString(true) );
		return war;
	}
