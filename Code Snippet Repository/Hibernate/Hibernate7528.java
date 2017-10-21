	@Test
	public void testProjectedListIncludesEmbeddedCompositeId() {
		Session s = openSession();
		Transaction t = s.beginTransaction();

		Course course = new Course();
		course.setCourseCode("HIB");
		course.setDescription("Hibernate Training");
		s.save(course);

		Student gavin = new Student();
		gavin.setName("Gavin King");
		gavin.setStudentNumber(667);
		s.save(gavin);

		Student xam = new Student();
		xam.setName("Max Rydahl Andersen");
		xam.setStudentNumber(101);
		s.save(xam);

		Enrolment enrolment = new Enrolment();
		enrolment.setCourse(course);
		enrolment.setCourseCode(course.getCourseCode());
		enrolment.setSemester((short) 1);
		enrolment.setYear((short) 1999);
		enrolment.setStudent(xam);
		enrolment.setStudentNumber(xam.getStudentNumber());
		xam.getEnrolments().add(enrolment);
		s.save(enrolment);

		enrolment = new Enrolment();
		enrolment.setCourse(course);
		enrolment.setCourseCode(course.getCourseCode());
		enrolment.setSemester((short) 3);
		enrolment.setYear((short) 1998);
		enrolment.setStudent(gavin);
		enrolment.setStudentNumber(gavin.getStudentNumber());
		gavin.getEnrolments().add(enrolment);
		s.save(enrolment);
		s.flush();
		List data = s.createCriteria( Enrolment.class)
				.setProjection( Projections.projectionList()
					.add( Projections.property( "semester" ) )
					.add( Projections.property("year") )
					.add( Projections.id() ) )
				.list();
		t.rollback();
		s.close();
	}
