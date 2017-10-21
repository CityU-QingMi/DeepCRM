	private void assertAccessType(SessionFactoryImplementor factory, Class<?> classUnderTest, AccessType accessType) {
		EntityTuplizer tuplizer = factory.getEntityPersister( classUnderTest.getName() )
				.getEntityMetamodel()
				.getTuplizer();
		if ( AccessType.FIELD.equals( accessType ) ) {
			Assert.assertTrue(
					"Field access was expected.",
					tuplizer.getGetter( 0 ) instanceof GetterFieldImpl
			);
		}
		else {
			Assert.assertTrue(
					"Property access was expected.",
					tuplizer.getGetter( 0 ) instanceof GetterMethodImpl
			);
		}
	}
