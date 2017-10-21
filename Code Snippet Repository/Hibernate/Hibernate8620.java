	@Test
	public void testOneToOne() throws Exception {
		Session s = openSession();
		s.beginTransaction();
		Lower ls = new Lower();
		Serializable id = s.save(ls);
		s.getTransaction().commit();
		s.close();

		s = openSession();
		s.beginTransaction();
		s.load(Lower.class, id);
		s.getTransaction().commit();
		s.close();

		s = openSession();
		s.beginTransaction();
		s.delete( s.load(Lower.class, id) );
		s.getTransaction().commit();
		s.close();
	}
