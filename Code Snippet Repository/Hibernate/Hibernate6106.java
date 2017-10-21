	@Test
	public void testExplicitPositionalParameter() throws Exception {
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
			Query query = em.createQuery( "select w from " + Wallet.class.getName() + " w where w.brand in ?1" );
			List brands = new ArrayList();
			brands.add( "Lacoste" );
			query.setParameter( 1, brands );
			w = (Wallet) query.getSingleResult();
			assertNotNull( w );
			query = em.createQuery( "select w from " + Wallet.class.getName() + " w where w.marketEntrance = ?1" );
			query.setParameter( 1, new Date(), TemporalType.DATE );
			//assertNull( query.getSingleResult() );
			assertEquals( 0, query.getResultList().size() );
			em.remove( w );
			em.getTransaction().commit();
		}
		catch (Exception e){
			if ( em.getTransaction() != null && em.getTransaction().isActive() ) {
				em.getTransaction().rollback();
			}
			throw e;
		}
		finally {
			em.close();
		}
	}
