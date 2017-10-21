	@Test
	public void getIdentifierOfNonEntityTest() {
		try {
			entityManagerFactory().getPersistenceUnitUtil().getIdentifier( this );
			fail( "should have thrown IllegalArgumentException" );
		}
		catch (IllegalArgumentException ex) {
			// expected
		}
	}
