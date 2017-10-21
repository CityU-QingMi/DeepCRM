	@Test
	@TestForIssue( jiraKey = "" )
	@SkipForDialect(value = AbstractHANADialect.class, comment = "")
	public void testCollectionInheritance() {
		Session s = openSession();
		Transaction t = s.beginTransaction();
		Zoo zoo = new Zoo();
		Mammal m = new Mammal();
		m.setMammalName( "name1" );
		m.setMammalName2( "name2" );
		m.setMammalName3( "name3" );
		m.setZoo( zoo );
		zoo.getAnimals().add( m );
		Long id = ( Long ) s.save( zoo );
		t.commit();
		s.close();

		s = openSession();
		t = s.beginTransaction();
		Zoo found = ( Zoo ) s.get( Zoo.class, id );
		found.getAnimals().size();
		s.delete( found );
		t.commit();
		s.close();
	}
