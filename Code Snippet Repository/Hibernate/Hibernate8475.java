	@Test
	public void testDiscriminatorFiltering() throws Exception {
		if ( ( getDialect() instanceof HSQLDialect ) ) return;
		Session s = openSession();
		Transaction t = s.beginTransaction();
		s.createQuery("from C1 c1 left join c1.c2s c2").list();
		s.createCriteria(C1.class).createCriteria("c2s").list();
		t.commit();
		s.close();
	}
