	@Test
	@TestForIssue(jiraKey = "")
	public void testNamedParameterWithUserError() throws Exception {
		EntityManager em = getOrCreateEntityManager();
		em.getTransaction().begin();
		try {
			Wallet w = new Wallet();
			w.setBrand( "Lacoste" );
			w.setModel( "Minimic" );
			w.setSerial( "0100202002" );
			em.persist( w );
			em.flush();

			Query jpaQuery = em.createQuery( "select w from Wallet w" );
			try {
				Parameter<?> parameter = jpaQuery.getParameter( "brand" );
				fail( "Should fail due to user error in parameters" );
			}
			catch (Exception e) {
				assertTyping( IllegalArgumentException.class, e );
			}

			jpaQuery = em.createQuery( "select w from Wallet w" );
			try {
				Parameter<String> parameter = jpaQuery.getParameter( "brand", String.class );
				fail( "Should fail due to user error in parameters" );
			}
			catch (Exception e) {
				assertTyping( IllegalArgumentException.class, e );
			}

		}
		finally {
			if ( em.getTransaction() != null && em.getTransaction().isActive() ) {
				em.getTransaction().rollback();
			}
			em.close();
		}
	}
