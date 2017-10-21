	@Test
	@TestForIssue( jiraKey = "" )
	public final void testUnderscoreInColumnName() throws Throwable {
		final Session s = openSession();
		s.getTransaction().begin();
		
		UserEntity user = new UserEntity();
		user.setName( "foo" );
		s.persist(user);
		final ConfEntity conf =  new ConfEntity();
		conf.setConfKey("counter");
		conf.setConfValue("3");
		final UserConfEntity uc = new UserConfEntity();
		uc.setUser(user);
		uc.setConf(conf);
		conf.getUserConf().add(uc);
		s.persist(conf);

		s.getTransaction().commit();
		s.clear();
		
		s.getTransaction().begin();
		user = (UserEntity) s.get(UserEntity.class, user.getId());

		try {
			s.flush();
		}
		catch ( HibernateException e ) {
			// original issue from HHH-8371
			fail( "The explicit column name's underscore(s) were not considered during alias creation." );
		}
		
		assertNotNull( user );
		assertEquals( user.getName(), "foo" );

		s.getTransaction().commit();
		s.close();
	}
