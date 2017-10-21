	@Test
	public void testOrdering() {
		Zoo zoo = new Zoo();
		Lion lion1 = new Lion();
		lion1.setWeight( 2 );
		Lion lion2 = new Lion();
		lion2.setWeight( 1 );
		zoo.getLions().add( lion1 );
		zoo.getLions().add( lion2 );
		zoo.getAnimalsById().add( lion1 );
		zoo.getAnimalsById().add( lion2 );
		
		Session session = openSession();
		session.beginTransaction();
		session.persist( lion1 );
		session.persist( lion2 );
		session.persist( zoo );
		session.getTransaction().commit();
		session.clear();
		
		session.beginTransaction();
		zoo = (Zoo) session.get( Zoo.class, zoo.getId() );
		zoo.getLions().size();
		zoo.getLions().size();
		zoo.getAnimalsById().size();
		session.getTransaction().commit();
		session.close();
		
		assertNotNull( zoo );
		assertTrue( CollectionHelper.isNotEmpty( zoo.getLions() ) && zoo.getLions().size() == 2 );
		assertTrue( CollectionHelper.isNotEmpty( zoo.getAnimalsById() ) && zoo.getAnimalsById().size() == 2 );
		assertEquals( zoo.getLions().iterator().next().getId(), lion2.getId() );
		assertEquals( zoo.getAnimalsById().iterator().next().getId(), lion1.getId() );
	}
