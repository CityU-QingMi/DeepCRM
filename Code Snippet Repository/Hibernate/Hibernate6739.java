	@Test
	public void testFetchModeOnSecondaryTable() throws Exception {
		Cat cat = new Cat();
		cat.setStoryPart2( "My long story" );
		Session s = openSession();
		Transaction tx = s.beginTransaction();

		s.persist( cat );
		s.flush();
		s.clear();
		
		s.get( Cat.class, cat.getId() );
		//Find a way to test it, I need to define the secondary table on a subclass

		tx.rollback();
		s.close();
	}
