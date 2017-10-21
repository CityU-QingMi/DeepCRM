	@Test
	@TestForIssue(jiraKey = "")
	public void testSelectWithDerivedId() {
		Session s = openSession();
		s.beginTransaction();
		Bar bar = new Bar();
		bar.setDetails( "Some details" );
		Foo foo = new Foo();
		foo.setBar( bar );
		bar.setFoo( foo );
		s.persist( foo );
		s.flush();
		assertNotNull( foo.getId() );
		assertEquals( foo.getId(), bar.getFoo().getId() );

		s.clear();
		Foo newFoo = (Foo) s.createQuery( "SELECT f FROM Foo f" ).uniqueResult();
		assertNotNull( newFoo );
		assertNotNull( newFoo.getBar() );
		assertSame( newFoo, newFoo.getBar().getFoo() );
		assertEquals( "Some details", newFoo.getBar().getDetails() );
		s.getTransaction().rollback();
		s.close();
	}
