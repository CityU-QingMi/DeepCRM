	@Test
	public void testCustom() throws Exception {
		GlarchProxy g = new Glarch();
		Multiplicity m = new Multiplicity();
		m.count = 12;
		m.glarch = g;
		g.setMultiple(m);

		Session s = openSession();
		s.beginTransaction();
		Serializable gid = s.save(g);
		s.getTransaction().commit();
		s.close();

		s = openSession();
		s.beginTransaction();
		//g = (Glarch) s.createQuery( "from Glarch g where g.multiple.count=12" ).list().get(0);
		s.createQuery( "from Glarch g where g.multiple.count=12" ).list().get( 0 );
		s.getTransaction().commit();
		s.close();

		s = openSession();
		s.beginTransaction();
		g = (Glarch) s.createQuery( "from Glarch g where g.multiple.glarch=g and g.multiple.count=12" ).list().get(0);
		assertTrue( g.getMultiple()!=null );
		assertEquals( g.getMultiple().count, 12 );
		assertSame(g.getMultiple().glarch, g);
		s.getTransaction().commit();
		s.close();

		s = openSession();
		s.beginTransaction();
		g = (GlarchProxy) s.load(Glarch.class, gid);
		assertTrue( g.getMultiple() != null );
		assertEquals( g.getMultiple().count, 12 );
		assertSame( g.getMultiple().glarch, g );
		s.delete(g);
		s.getTransaction().commit();
		s.close();
	}
