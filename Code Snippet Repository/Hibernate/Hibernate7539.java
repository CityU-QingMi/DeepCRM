	@Test
	public void testClassProperty2() {
		Session session = openSession();
		Transaction t = session.beginTransaction();
		GreatFoo foo = new GreatFoo();
		Bar b = new Bar();
		b.setMyFoo(foo);
		foo.setId(1);
		b.setId(1);
		session.persist(b);
		session.flush();
		t.commit();
		session=openSession();
		t=session.beginTransaction();
		// OK, one BAR in DB
		assertEquals(1, session.createCriteria(Bar.class).list().size());
		Criteria crit = session.createCriteria(Bar.class, "b").createAlias(
				"myFoo", "m").add(
				Property.forName("m.class").eq(GreatFoo.class));
		assertEquals(1, crit.list().size());
		crit = session.createCriteria(Bar.class, "b").createAlias("myFoo", "m")
				.add(Restrictions.eq("m.class", GreatFoo.class));
		assertEquals(1, crit.list().size());
		t.commit();
		session.close();
	}
