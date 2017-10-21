	@Test
	public void testInvalidPrimaryKeyJoinColumnAnnotationMessageContainsClassName() throws Exception {

		Triggerable triggerable = logInspection.watchForLogMessages( "HHH000137" );

		StandardServiceRegistryBuilder srb = new StandardServiceRegistryBuilder();

		Metadata metadata = new MetadataSources( srb.build() )
				.addAnnotatedClass( InvalidPrimaryKeyJoinColumnAnnotationEntity.class )
				.buildMetadata();

		assertTrue( "Expected warning HHH00137 but it wasn't triggered", triggerable.wasTriggered() );
		assertTrue( "Expected invalid class name in warning HHH00137 message but it does not apper to be present; got " + triggerable.triggerMessage(),
				triggerable.triggerMessage().matches( ".*\\b\\Q" + InvalidPrimaryKeyJoinColumnAnnotationEntity.class.getName() + "\\E\\b.*" ) );
	}
