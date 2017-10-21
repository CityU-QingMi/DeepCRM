	@Test
	public void testNoUpdateManyToOne() throws Exception {
		Session s = openSession();
		s.beginTransaction();
		W w1 = new W();
		W w2 = new W();
		Z z = new Z();
		z.setW(w1);
		s.save(z);
		s.flush();
		z.setW(w2);
		s.getTransaction().commit();
		s.close();

		s = openSession();
		s.beginTransaction();
		s.update(z);
		s.flush();
		s.delete(z);
		for ( Object entity : s.createQuery( "from W" ).list() ) {
			s.delete( entity );
		}
		s.getTransaction().commit();
		s.close();
	}
