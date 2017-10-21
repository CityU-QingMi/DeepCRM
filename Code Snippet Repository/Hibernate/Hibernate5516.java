	@Test
	public void testFactoryClosed() throws Exception {
		EntityManager em = createIsolatedEntityManager();
		assertTrue( em.isOpen() );
		assertTrue( em.getEntityManagerFactory().isOpen());

		em.getEntityManagerFactory().close();	// closing the entity manager factory should close the EM
		assertFalse(em.isOpen());

		try {
			em.close();
			fail("closing entity manager that uses a closed session factory, must throw IllegalStateException");
		}
		catch( IllegalStateException expected) {
			// success
		}
	}
