	public void testMapKeyOnManyToMany() throws Exception {
		Session s;
		s = openSession();
		s.getTransaction().begin();
		News airplane = new News();
		airplane.setTitle( "Crash!" );
		airplane.setDetail( "An airplaned crashed." );
		s.persist( airplane );
		Newspaper lemonde = new Newspaper();
		lemonde.setName( "Lemonde" );
		lemonde.getNews().put( airplane.getTitle(), airplane );
		s.persist( lemonde );

		s.flush();
		s.clear();

		lemonde = (Newspaper) s.get( Newspaper.class, lemonde.getId() );
		assertEquals( 1, lemonde.getNews().size() );
		News news = lemonde.getNews().get( airplane.getTitle() );
		assertNotNull( news );
		assertEquals( airplane.getTitle(), news.getTitle() );
		s.delete( lemonde );
		s.delete( news );

		s.getTransaction().rollback();
		s.close();
	}
