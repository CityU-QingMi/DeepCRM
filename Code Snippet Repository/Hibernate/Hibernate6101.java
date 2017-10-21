	@Test
	public void testJpaPositionalParameters() {
		EntityManager em = getOrCreateEntityManager();
		em.getTransaction().begin();
		try {
			Query query = em.createQuery( "from Item item where item.name =?1 or item.descr = ?1" );
			Parameter p1 = query.getParameter( 1 );
			Assert.assertNotNull( p1 );
			// in 5.2, '?<position' parameters are named while '?' are position-based.
			Assert.assertNotNull( p1.getName() );
			Assert.assertNull( p1.getPosition() );

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
