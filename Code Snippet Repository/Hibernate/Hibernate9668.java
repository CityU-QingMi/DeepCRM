	@Deployment
	public static WebArchive deploy() throws Exception {
		final WebArchive war = ShrinkWrap.create( WebArchive.class, ARCHIVE_NAME + ".war" )
				.setManifest( "org/hibernate/test/wf/ddl/manifest.mf" )
				.addClasses( WildFlyDdlEntity.class )
				.addAsResource( new StringAsset( hibernate_cfg ), "hibernate.cfg.xml" )
				.addClasses( BmtSfStatefulBean.class )
				.addClasses( DdlInWildFlyUsingBmtAndSfTest.class );
		return war;
	}
