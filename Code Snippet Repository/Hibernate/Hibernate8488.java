	@Test
    @RequiresDialectFeature( NonIdentityGeneratorChecker.class )
    @SkipForDialect( value = {PostgreSQL81Dialect.class, PostgreSQLDialect.class}, jiraKey = "")
	public void testInsert() throws HibernateException, SQLException {
		Session s = openSession();
		s.beginTransaction();
		Role p = new Role();
		p.setName("Patient");
		s.save( p );
		s.getTransaction().commit();
		s.close();

		sessionFactory().getCache().evictEntityRegion( Role.class );

		s = openSession();
		s.beginTransaction();
		Role p2 = (Role) s.get(Role.class, Long.valueOf(p.getId()));
		assertNotSame(p, p2);
		assertEquals(p2.getId(),p.getId());
		assertTrue(p2.getName().equalsIgnoreCase(p.getName()));
		s.delete(p2);
		s.getTransaction().commit();
		s.close();
	}
