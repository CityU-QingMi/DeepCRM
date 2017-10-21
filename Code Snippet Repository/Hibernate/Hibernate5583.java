	@Deployment
	public static Archive<?> buildDeployment() {
		return ShrinkWrap.create( JavaArchive.class, "test.jar" )
				.addClass( MyEntity.class )
				.addClass( EventQueue.class )
				.addClass( Event.class )
				.addClass( Monitor.class )
				.addAsManifestResource( "jboss-deployment-structure.xml" )
				.addAsManifestResource( EmptyAsset.INSTANCE, "beans.xml" )
				.addAsManifestResource( new StringAsset( persistenceXml().exportAsString() ), "persistence.xml" );
	}
