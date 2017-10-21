	@Test
	public void testClassProperty() {
		Session s = openSession();
		Transaction t = s.beginTransaction();

		// HQL: from Animal a where a.mother.class = Reptile
		Criteria c = s.createCriteria(Animal.class,"a")
			.createAlias("mother","m")
			.add( Property.forName("m.class").eq(Reptile.class) );
		c.list();
		t.rollback();
		s.close();
	}
