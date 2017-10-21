	@Test
	@TestForIssue(jiraKey = "")
	public void testProperties() {
		Session s = openSession();
		Transaction t = s.beginTransaction();
		EntityClass ec = new EntityClass();
		ec.setKey( 1l );
		ec.setField1( "foo1" );
		ec.setField2( "foo2" );
		s.persist( ec );
		t.commit();
		s.close();

		s = openSession();
		t = s.beginTransaction();
		ec = (EntityClass) s.get( EntityClass.class, 1l );
		t.commit();
		s.close();
		
		assertNotNull( ec );
		assertEquals( ec.getField1(), "foo1" );
		assertEquals( ec.getField2(), "foo2" );
	}
