	@Test
	public void testDiscardOnClose() throws SQLException {
		EntityManager em = entityManagerFactory().createEntityManager();
		Wallet wallet = new Wallet();
		wallet.setSerial( "123" );

		try {
			em.getTransaction().begin();
			em.persist( wallet );
			assertEquals( 1, connectionProvider.getAcquiredConnections().size() );
			em.close();
			assertEquals( 1, connectionProvider.getAcquiredConnections().size() );
			assertTrue(em.getTransaction().isActive());
		}
		finally {
			assertEquals( 1, connectionProvider.getAcquiredConnections().size() );
			em.getTransaction().rollback();
			assertEquals( 0, connectionProvider.getAcquiredConnections().size() );
			assertFalse(em.getTransaction().isActive());
		}
	}
