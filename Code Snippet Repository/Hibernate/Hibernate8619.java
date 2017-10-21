	@Test
	public void testCriteria() throws Exception {
		Session s = openSession();
		Transaction t = s.beginTransaction();
		Lower l = new Lower();
		s.save(l);
		assertTrue( l==s.createCriteria(Top.class).uniqueResult() );
		s.delete(l);
		s.flush();
		Criteria c = s.createCriteria(Lower.class);
		c.createCriteria("yetanother")
			.add( Restrictions.isNotNull("id") )
			.createCriteria("another");
		c.createCriteria("another").add( Restrictions.isNotNull("id") );
		c.list();
		t.commit();
		s.close();
	}
