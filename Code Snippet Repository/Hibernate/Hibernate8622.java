	@Test
	public void testNarrow() throws Exception {
		Session s = openSession();
		s.beginTransaction();
		s.createQuery("from Po po, Lower low where low.mypo = po").list();
		s.createQuery("from Po po join po.set as sm where sm.amount > 0").list();
		s.createQuery("from Po po join po.top as low where low.foo = 'po'").list();
		s.getTransaction().commit();
		s.close();
	}
