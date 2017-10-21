	@Test
	public void testEntityManagerCannotBeUnwrappedToUnrelatedType() {
		try {
			entityManagerFactory.unwrap( EntityManager.class );
			fail( "It should not be possible to unwrap to unrelated type." );
		}
		catch ( PersistenceException e ) {
			// ignore
		}
	}
