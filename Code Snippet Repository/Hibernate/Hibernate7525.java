	@Test
	public void testProjectedCompositeIdWithAlias() {
		Session s = openSession();
		Transaction t = s.beginTransaction();

		Course course = new Course();
		course.setCourseCode( "HIB" );
		course.setDescription( "Hibernate Training" );
		course.getCourseMeetings().add( new CourseMeeting( course, "Monday", 1, "1313 Mockingbird Lane" ) );
		s.save(course);
		s.flush();

		List data = s.createCriteria( CourseMeeting.class).setProjection( Projections.id().as( "id" ) ).list();
		t.rollback();
		s.close();
	}
