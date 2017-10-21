	@Test
	@TestForIssue( jiraKey = "" )
	@SuppressWarnings( {""})
	public void testSuperclassPropertyReferenceAfterCollectionIndexedAccess() {
		// note: simply performing syntax checking in the db
		Session s = openSession();
		s.beginTransaction();
		Mammal tiger = new Mammal();
		tiger.setDescription( "Tiger" );
		s.persist( tiger );
		Mammal mother = new Mammal();
		mother.setDescription( "Tiger's mother" );
		mother.setBodyWeight( 4.0f );
		mother.addOffspring( tiger );
		s.persist( mother );
		Zoo zoo = new Zoo();
		zoo.setName( "Austin Zoo" );
		zoo.setMammals( new HashMap() );
		zoo.getMammals().put( "tiger", tiger );
		s.persist( zoo );
		s.getTransaction().commit();
		s.close();

		s = openSession();
		s.beginTransaction();
		List results = s.createQuery( "from Zoo zoo where zoo.mammals['tiger'].mother.bodyWeight > 3.0f" ).list();
		assertEquals( 1, results.size() );
		s.getTransaction().commit();
		s.close();

		s = openSession();
		s.beginTransaction();
		s.delete( tiger );
		s.delete( mother );
		s.delete( zoo );
		s.getTransaction().commit();
		s.close();
	}
