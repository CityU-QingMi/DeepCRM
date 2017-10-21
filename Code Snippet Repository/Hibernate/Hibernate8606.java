	@Test
	public void testInterface() throws Exception {
		Session s = openSession();
		s.beginTransaction();
		Serializable id = s.save( new BasicNameable() );
		s.getTransaction().commit();
		s.close();

		s = openSession();
		s.beginTransaction();
		Nameable n = (Nameable) s.load(Nameable.class, id);
		s.delete(n);
		s.getTransaction().commit();
		s.close();
	}
