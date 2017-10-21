    @Test
    @TestForIssue( jiraKey = "" )
    public void testNotNot() {
    	Student student1 = new Student();
    	student1.setName("Foo1 Foo1");
    	student1.setStudentNumber(1);
    	Student student2 = new Student();
    	student2.setName("Foo2 Foo2");
    	student2.setStudentNumber(2);
    	
    	Session s = openSession();
		Transaction t = s.beginTransaction();
		
		s.persist( student1 );
		s.persist( student2 );
		s.flush();
		s.clear();
		
		// Although this example is simplified and the "not not" is pointless,
		// double negatives can occur in some dynamic applications (regardless
		// if it results from bad design or not).  Test to ensure the dialect
		// handles them as expected.
		List<Student> students = s.createCriteria( Student.class ).add(
				Restrictions.not(
						Restrictions.not(
								Restrictions.eq( "studentNumber", 1l ) ) )
		).list();
		
		assertEquals( students.size(), 1 );
		assertEquals( students.get( 0 ).getStudentNumber(), 1 );
		
		t.rollback();
		s.close();
    }
