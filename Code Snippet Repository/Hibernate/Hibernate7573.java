	@Test
	public void testIgnoreCaseCriteria() {

		User user1 = new User(1, "Chris");
		User user2 = new User(2, "Steve");

		doInHibernate( this::sessionFactory, session -> {
			session.save(user1);
			session.save(user2);
		} );

		doInHibernate( this::sessionFactory, session -> {
			Criteria criteria = session.createCriteria(User.class);
			criteria.add(Restrictions.eq("name", user1.getName().toLowerCase()).ignoreCase());
			assertEquals(1, criteria.list().size());
		} );
	}
