	@Test
	public void testConstraintViolationException() throws Exception {
		EntityManager em = getOrCreateEntityManager();
		em.getTransaction().begin();
		Music music = new Music();
		music.setName( "Jazz" );
		em.persist( music );
		Musician lui = new Musician();
		lui.setName( "Lui Armstrong" );
		lui.setFavouriteMusic( music );
		em.persist( lui );
		em.getTransaction().commit();
		try {
			em.getTransaction().begin();
			String hqlDelete = "delete Music where name = :name";
			em.createQuery( hqlDelete ).setParameter( "name", "Jazz" ).executeUpdate();
			em.getTransaction().commit();
			fail();
		}
		catch ( PersistenceException e ) {
			Throwable t = e.getCause();
			assertTrue( "Should be a constraint violation", t instanceof ConstraintViolationException );
			em.getTransaction().rollback();
		}
		finally {
			em.close();
		}
	}
