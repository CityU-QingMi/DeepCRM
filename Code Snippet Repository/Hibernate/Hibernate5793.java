	@Test
	public void testDiscardOnClose() {
		EntityManager em = entityManagerFactory().createEntityManager();
		Wallet wallet = new Wallet();
		wallet.setSerial( "123" );

		try {
			em.getTransaction().begin();
			em.persist( wallet );
			assertEquals( 1, connectionProvider.getAcquiredConnections().size() );
			em.close();
			assertEquals( 0, connectionProvider.getAcquiredConnections().size() );
			assertTrue(em.getTransaction().isActive());
		}
		finally {
			try {
				em.getTransaction().rollback();
				fail("Should throw IllegalStateException because the Connection is already closed!");
			}
			catch ( IllegalStateException expected ) {
			}
		}
	}
