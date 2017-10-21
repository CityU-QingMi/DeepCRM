    @Test
	public void testCriteriaCollectionOfValue() {
		Session session = openSession();
		Transaction t = session.beginTransaction();

		Course course = new Course();
		course.setCourseCode("HIB");
		course.setDescription("Hibernate Training");
		Set crossListedAs = new HashSet();
		crossListedAs.add("Java Persistence 101");
		crossListedAs.add("CS101");
		course.setCrossListedAs(crossListedAs);
		session.persist(course);
		session.flush();
		session.clear();
		List results = session.createCriteria(Course.class)
		    .createCriteria("crossListedAs")
		    .add(Restrictions.eq("elements", "CS101"))
		    .list();

		assertEquals( 1, results.size() );
		course = (Course)results.get(0);
		assertEquals( 2, course.getCrossListedAs().size() );

		session.delete(course);

		t.commit();
		session.close();

	}
