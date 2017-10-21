	@Test
	@SkipForDialect(value = AbstractHANADialect.class, comment = "")
	public void testPolymorphicCriteria() throws Exception {
		Session s = openSession();
		Transaction txn = s.beginTransaction();
		Category f = new Category();
		Single b = new Single();
		b.setId("asdfa");
		b.setString("asdfasdf");
		s.save(f);
		s.save(b);
		List list = s.createCriteria(Object.class).list();
		assertTrue( list.size()==2 );
		assertTrue( list.contains(f) && list.contains(b) );
		s.delete(f);
		s.delete(b);
		txn.commit();
		s.close();
	}
