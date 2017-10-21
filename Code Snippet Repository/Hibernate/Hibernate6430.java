	@Test
	@TestForIssue( jiraKey = "")
	public void testInsertFooAndBarWithDerivedIdPC() {
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
		Bar barWithFoo = new Bar();
		barWithFoo.setFoo( foo );
		barWithFoo.setDetails( "wrong details" );
		bar = (Bar) s.get( Bar.class, barWithFoo );
		assertSame( bar, barWithFoo );
		assertEquals( "Some details", bar.getDetails() );
		SessionImplementor si = (SessionImplementor) s;
		assertTrue( si.getPersistenceContext().isEntryFor( bar ) );
		assertFalse( si.getPersistenceContext().isEntryFor( bar.getFoo() ) );

		s.getTransaction().rollback();
		s.close();
	}
