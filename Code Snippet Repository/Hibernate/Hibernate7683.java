	@Test
	@TestForIssue( jiraKey = "" )
	public void testMerging() {
		Session s = openSession();
		Transaction t = s.beginTransaction();
		Course course = new Course( "EN-101", "BA", "preparatory english" );
		s.persist( course );
		t.commit();
		s.close();

		String newDesc = "basic preparatory english";
		course.setDescription( newDesc );

		s = openSession();
		t = s.beginTransaction();
		Course c = (Course) s.merge( course );
		assertEquals( "description not merged", newDesc, c.getDescription() );
		t.commit();
		s.close();

		s = openSession();
		t = s.beginTransaction();
		Course cid = new Course( "EN-101", "BA", null );
		course = ( Course ) s.get( Course.class, cid );
		assertEquals( "description not merged", newDesc, course.getDescription() );
		s.delete( course );
		t.commit();
		s.close();
	}
