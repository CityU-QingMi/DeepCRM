	public void testMapKeyOnManyToManyOnId() throws Exception {
		Session s;
		s = openSession();
		s.getTransaction().begin();
		News hibernate1 = new News();
		hibernate1.setTitle( "#1 ORM solution in the Java world" );
		hibernate1.setDetail( "Well, that's no news ;-)" );
		s.persist( hibernate1 );
		PressReleaseAgency schwartz = new PressReleaseAgency();
		schwartz.setName( "Schwartz" );
		schwartz.getProvidedNews().put( hibernate1.getId(), hibernate1 );
		s.persist( schwartz );

		s.flush();
		s.clear();

		schwartz = (PressReleaseAgency) s.get( PressReleaseAgency.class, schwartz.getId() );
		assertEquals( 1, schwartz.getProvidedNews().size() );
		News news = schwartz.getProvidedNews().get( hibernate1.getId() );
		assertNotNull( news );
		assertEquals( hibernate1.getTitle(), news.getTitle() );
		s.delete( schwartz );
		s.delete( news );

		s.getTransaction().rollback();
		s.close();
	}
