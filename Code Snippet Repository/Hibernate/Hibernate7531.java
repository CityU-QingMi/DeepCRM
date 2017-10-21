    @Test
    @TestForIssue( jiraKey = "" )
    public void testNullCriteria() {
    	Course course = new Course();
    	course.setCourseCode( "1234" );
    	course.setDescription( null );
    	
    	Session s = openSession();
		Transaction t = s.beginTransaction();
		
		s.persist( course );
		s.flush();
		s.clear();
		
		// Ensure Restrictions creates "where foo is null", instead of
		// "where foo = null"
		List<Course> courses = s.createCriteria( Course.class ).add(
				Restrictions.eqOrIsNull( "description", null) ).list();
		
		assertEquals( courses.size(), 1 );
		assertEquals( courses.get( 0 ).getCourseCode(), course.getCourseCode() );
		
		t.rollback();
		s.close();
    }
