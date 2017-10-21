	@Test
	public void testDyna() throws Exception {
		Session s = openSession();
		s.beginTransaction();
		GlarchProxy g = new Glarch();
		g.setName("G");
		Serializable id = s.save(g);
		s.getTransaction().commit();
		s.close();

		s = openSession();
		s.beginTransaction();
		g = (GlarchProxy) s.load(Glarch.class, id);
		assertTrue( g.getName().equals("G") );
		assertTrue( g.getDynaBean().get("foo").equals("foo") && g.getDynaBean().get("bar").equals( new Integer(66) ) );
		assertTrue( ! (g instanceof Glarch) );
		g.getDynaBean().put("foo", "bar");
		s.getTransaction().commit();
		s.close();

		s = openSession();
		s.beginTransaction();
		g = (GlarchProxy) s.load(Glarch.class, id);
		assertTrue( g.getDynaBean().get("foo").equals("bar") && g.getDynaBean().get("bar").equals( new Integer(66) ) );
		g.setDynaBean(null);
		s.getTransaction().commit();
		s.close();

		s = openSession();
		s.beginTransaction();
		g = (GlarchProxy) s.load(Glarch.class, id);
		assertTrue( g.getDynaBean()==null );
		s.delete(g);
		s.getTransaction().commit();
		s.close();
	}
