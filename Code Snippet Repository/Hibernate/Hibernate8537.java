	@Test
	public void testVersioning() throws Exception {
		Session s = openSession();
		Transaction txn = s.beginTransaction();
		GlarchProxy g = new Glarch();
		s.save(g);
		GlarchProxy g2 = new Glarch();
		s.save(g2);
		Serializable gid = s.getIdentifier(g);
		Serializable g2id = s.getIdentifier(g2);
		g.setName("glarch");
		txn.commit();
		s.close();

		sessionFactory().getCache().evictEntityRegion(Glarch.class);

		s = openSession();
		txn = s.beginTransaction();
		g = (GlarchProxy) s.load( Glarch.class, gid );
		s.lock(g, LockMode.UPGRADE);
		g2 = (GlarchProxy) s.load( Glarch.class, g2id );
		assertTrue( "version", g.getVersion()==1 );
		assertTrue( "version", g.getDerivedVersion()==1 );
		assertTrue( "version", g2.getVersion()==0 );
		g.setName("foo");
		assertTrue(
			"find by version",
				s.createQuery( "from Glarch g where g.version=2" ).list().size()==1
		);
		g.setName("bar");
		txn.commit();
		s.close();

		sessionFactory().getCache().evictEntityRegion(Glarch.class);

		s = openSession();
		txn = s.beginTransaction();
		g = (GlarchProxy) s.load( Glarch.class, gid );
		g2 = (GlarchProxy) s.load( Glarch.class, g2id );
		assertTrue( "version", g.getVersion()==3 );
		assertTrue( "version", g.getDerivedVersion()==3 );
		assertTrue( "version", g2.getVersion()==0 );
		g.setNext(null);
		g2.setNext(g);
		s.delete(g2);
		s.delete(g);
		txn.commit();
		s.close();
	}
