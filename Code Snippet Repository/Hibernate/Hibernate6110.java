	@Test
	public void testNativeQuestionMarkParameter() throws Exception {
		EntityManager em = getOrCreateEntityManager();
		em.getTransaction().begin();
		try {
			Wallet w = new Wallet();
			w.setBrand( "Lacoste" );
			w.setModel( "Minimic" );
			w.setSerial( "0100202002" );
			em.persist( w );
			em.getTransaction().commit();
			em.getTransaction().begin();
			Query query = em.createNativeQuery( "select * from Wallet w where w.brand = ?", Wallet.class );
			query.setParameter( 1, "Lacoste" );
			w = (Wallet) query.getSingleResult();
			assertNotNull( w );
			em.remove( w );
			em.getTransaction().commit();
		}
		catch (Exception e) {
			if ( em.getTransaction() != null && em.getTransaction().isActive() ) {
				em.getTransaction().rollback();
			}
			throw e;
		}
		finally {

			em.close();
		}
	}
