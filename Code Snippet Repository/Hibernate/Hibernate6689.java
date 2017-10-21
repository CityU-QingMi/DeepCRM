	@Test
	public void testSortedMap() {
		Session s = openSession();
		Transaction tx = s.beginTransaction();
		Training training = new Training();
		Trainee trainee = new Trainee();
		trainee.setName( "Jim" );
		Trainee trainee2 = new Trainee();
		trainee2.setName( "Emmanuel" );
		s.persist( trainee );
		s.persist( trainee2 );
		training.getTrainees().put( "Jim", trainee );
		training.getTrainees().put( "Emmanuel", trainee2 );
		s.persist( training );

		s.flush();
		s.clear();

		training = (Training) s.get( Training.class, training.getId() );
		assertEquals( "Emmanuel", training.getTrainees().firstKey() );
		assertEquals( "Jim", training.getTrainees().lastKey() );
		tx.rollback();
		s.close();
	}
