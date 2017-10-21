	@Test
	public void testScrollCriteria() {
		Session session = openSession();
		Transaction t = session.beginTransaction();

		Course course = new Course();
		course.setCourseCode("HIB");
		course.setDescription("Hibernate Training");
		session.persist(course);
		session.flush();
		session.clear();
		ScrollableResults sr = session.createCriteria(Course.class).setReadOnly( true ).scroll();
		assertTrue( sr.next() );
		course = (Course) sr.get(0);
		assertNotNull(course);
		assertTrue( session.isReadOnly( course ) );
		sr.close();
		session.delete(course);

		t.commit();
		session.close();
	}
