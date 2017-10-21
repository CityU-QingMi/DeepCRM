	@Test
	public void testPersistNoCascadeToTransient() {
		skipCleanup = true;
		EntityManager em = getOrCreateEntityManager();
		em.getTransaction().begin();
		try {
			em.persist( c );
			fail( "should have failed." );
		}
		catch( IllegalStateException ex ) {
			assertTrue( TransientPropertyValueException.class.isInstance( ex.getCause() ) );
			TransientPropertyValueException pve = (TransientPropertyValueException) ex.getCause();
			assertEquals( G.class.getName(), pve.getTransientEntityName() );
			assertEquals( F.class.getName(),  pve.getPropertyOwnerEntityName() );
			assertEquals( "g", pve.getPropertyName() );
		}
		em.getTransaction().rollback();
		em.close();
	}
