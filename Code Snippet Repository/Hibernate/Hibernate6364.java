	@Test
	public void testMerge() {
		Session s = openSession();
		s.getTransaction().begin();
		b = (B) s.merge( b );
		c = b.getC();
		d = b.getD();
		e = d.getE();
		f = e.getF();
		g = f.getG();
		s.getTransaction().commit();
		s.close();

		check();
	}
