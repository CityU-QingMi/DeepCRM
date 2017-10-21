	@Test
	public void getIdentifierOfNullTest() {
		try {
			entityManagerFactory().getPersistenceUnitUtil().getIdentifier( null );
			fail( "should have thrown IllegalArgumentException" );
		}
		catch (IllegalArgumentException ex) {
			// expected
		}
	}
