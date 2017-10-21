	@Test
	public void testMerge() {
		EntityManager em = getOrCreateEntityManager();
		em.getTransaction().begin();
		b = em.merge( b );
		c = b.getC();
		d = b.getD();
		e = d.getE();
		f = e.getF();
		g = f.getG();
		em.getTransaction().commit();
		em.close();

		check();
	}
