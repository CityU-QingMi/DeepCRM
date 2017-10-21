	@Test
	public void testOptimisticLockingException() throws Exception {
		EntityManager em = getOrCreateEntityManager();
		EntityManager em2 = entityManagerFactory().createEntityManager();
		em.getTransaction().begin();
		Music music = new Music();
		music.setName( "Old Country" );
		em.persist( music );
		em.getTransaction().commit();

		try {
			em2.getTransaction().begin();
			Music music2 = em2.find( Music.class, music.getId() );
			music2.setName( "HouseMusic" );
			em2.getTransaction().commit();
		}
		catch ( Exception e ) {
			em2.getTransaction().rollback();
			throw e;
		}
		finally {
			em2.close();
		}

		em.getTransaction().begin();
		music.setName( "Rock" );
		try {

			em.flush();
			fail( "Should raise an optimistic lock exception" );
		}
		catch ( OptimisticLockException e ) {
			//success
			assertEquals( music, e.getEntity() );
		}
		catch ( Exception e ) {
			fail( "Should raise an optimistic lock exception" );
		}
		finally {
			em.getTransaction().rollback();
			em.close();
		}
	}
