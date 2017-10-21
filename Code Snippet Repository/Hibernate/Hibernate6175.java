	@Test
	public void testCloseAndTransaction() throws Exception {
		EntityManager em = getOrCreateEntityManager();
		em.getTransaction().begin();
		Book book = new Book();
		book.name = "Java for Dummies";
		em.close();

		assertFalse( em.isOpen() );
		try {
			em.flush();
			fail( "direct action on a closed em should fail" );
		}
		catch ( IllegalStateException e ) {
			//success
		}
	}
