	@Test
	public void testDeleteAndMerge() throws Exception {
		Session s = openSession();
		s.getTransaction().begin();
		Employer jboss = new Employer();
		s.persist( jboss );
		s.getTransaction().commit();
		s.clear();

		s.getTransaction().begin();
		Employer otherJboss;
		otherJboss = (Employer) s.get( Employer.class, jboss.getId() );
		s.delete( otherJboss );
		s.getTransaction().commit();
		s.clear();
		jboss.setVers( Integer.valueOf( 1 ) );
		s.getTransaction().begin();
		s.merge( jboss );
		s.getTransaction().commit();
		s.close();

		cleanup();
	}
