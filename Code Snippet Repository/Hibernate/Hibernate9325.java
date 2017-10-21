	@Test
	public void testSimpleColumnAddition() {
		String resource1 = "org/hibernate/test/schemaupdate/1_Version.hbm.xml";
		String resource2 = "org/hibernate/test/schemaupdate/2_Version.hbm.xml";

		MetadataImplementor v1metadata = (MetadataImplementor) new MetadataSources( serviceRegistry )
				.addResource( resource1 )
				.buildMetadata();

		new SchemaExport().drop( EnumSet.of( TargetType.DATABASE ), v1metadata );

		final SchemaUpdate v1schemaUpdate = new SchemaUpdate();
		v1schemaUpdate.execute(
				EnumSet.of( TargetType.DATABASE, TargetType.STDOUT ),
				v1metadata
		);

		assertEquals( 0, v1schemaUpdate.getExceptions().size() );

		MetadataImplementor v2metadata = (MetadataImplementor) new MetadataSources( serviceRegistry )
				.addResource( resource2 )
				.buildMetadata();

		final SchemaUpdate v2schemaUpdate = new SchemaUpdate();
		v2schemaUpdate.execute(
				EnumSet.of( TargetType.DATABASE, TargetType.STDOUT ),
				v2metadata
		);
		assertEquals( 0, v2schemaUpdate.getExceptions().size() );
		
		new SchemaExport().drop( EnumSet.of( TargetType.DATABASE ), v2metadata );

	}
