	@Test
	public void testPersistThenUpdateNoCascadeToTransient() {
		// expected to fail, so nothing will be persisted.
		skipCleanup = true;

		// remove elements from collections and persist
		c.getBCollection().clear();
		c.getDCollection().clear();

		EntityManager em = getOrCreateEntityManager();
		em.getTransaction().begin();
		em.persist( c );
		// now add the elements back
		c.getBCollection().add( b );
		c.getDCollection().add( d );
		try {
			em.getTransaction().commit();
			fail( "should have thrown IllegalStateException");
		}
		catch ( RollbackException ex ) {
			assertTrue( ex.getCause() instanceof IllegalStateException );
			IllegalStateException ise = ( IllegalStateException ) ex.getCause();
			// should fail on entity g (due to no cascade to f.g);
			// instead it fails on entity e ( due to no cascade to d.e)
			// because e is not in the process of being saved yet.
			// when HHH-6999 is fixed, this test should be changed to
			// check for g and f.g
			//noinspection ThrowableResultOfMethodCallIgnored
			TransientPropertyValueException tpve = assertTyping( TransientPropertyValueException.class, ise.getCause() );
			assertEquals( E.class.getName(), tpve.getTransientEntityName() );
			assertEquals( D.class.getName(), tpve.getPropertyOwnerEntityName() );
			assertEquals( "e", tpve.getPropertyName() );
		}
		em.close();
	}
