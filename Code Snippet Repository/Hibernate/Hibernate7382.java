	@Test
	public void testCustomColumnReadAndWrite() {
		Session s = openSession();
		Transaction t = s.beginTransaction();
		User u = new User( "steve", "hibernater", new Person( "Steve Ebersole", new Date(), "Main St") );
		final double HEIGHT_INCHES = 73;
		final double HEIGHT_CENTIMETERS = HEIGHT_INCHES * 2.54d;
		u.getPerson().setHeightInches(HEIGHT_INCHES);
		s.persist( u );
		s.flush();
		
		// Test value conversion during insert
		// Value returned by Oracle native query is a Types.NUMERIC, which is mapped to a BigDecimalType;
		// Cast returned value to Number then call Number.doubleValue() so it works on all dialects.
		Double heightViaSql =
				( (Number)s.createSQLQuery("select height_centimeters from T_USER where T_USER.username='steve'").uniqueResult())
						.doubleValue();
		assertEquals(HEIGHT_CENTIMETERS, heightViaSql, 0.01d);

		// Test projection
		Double heightViaHql = (Double)s.createQuery("select u.person.heightInches from User u where u.id = 'steve'").uniqueResult();
		assertEquals(HEIGHT_INCHES, heightViaHql, 0.01d);
		
		// Test restriction and entity load via criteria
		u = (User)s.createCriteria(User.class)
			.add(Restrictions.between("person.heightInches", HEIGHT_INCHES - 0.01d, HEIGHT_INCHES + 0.01d))
			.uniqueResult();
		assertEquals(HEIGHT_INCHES, u.getPerson().getHeightInches(), 0.01d);
		
		// Test predicate and entity load via HQL
		u = (User)s.createQuery("from User u where u.person.heightInches between ? and ?")
			.setDouble(0, HEIGHT_INCHES - 0.01d)
			.setDouble(1, HEIGHT_INCHES + 0.01d)
			.uniqueResult();
		assertEquals(HEIGHT_INCHES, u.getPerson().getHeightInches(), 0.01d);
		
		// Test update
		u.getPerson().setHeightInches(1);
		s.flush();
		heightViaSql =
				( (Number)s.createSQLQuery("select height_centimeters from T_USER where T_USER.username='steve'").uniqueResult() )
						.doubleValue();
		assertEquals(2.54d, heightViaSql, 0.01d);
		s.delete(u);
		t.commit();
		s.close();
	}
