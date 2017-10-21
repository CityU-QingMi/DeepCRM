	@Test
	public void testProxyArray() throws Exception {
		Session s = openSession();
		s.beginTransaction();
		GlarchProxy g = new Glarch();
		Glarch g1 = new Glarch();
		Glarch g2 = new Glarch();
		g.setProxyArray( new GlarchProxy[] { g1, g2 } );
		Glarch g3 = new Glarch();
		s.save(g3);
		g2.setProxyArray( new GlarchProxy[] {null, g3, g} );
		Set set = new HashSet();
		set.add(g1);
		set.add(g2);
		g.setProxySet(set);
		s.save(g);
		s.save(g1);
		s.save(g2);
		Serializable id = s.getIdentifier(g);
		s.getTransaction().commit();
		s.close();

		s = openSession();
		s.beginTransaction();
		g = (GlarchProxy) s.load(Glarch.class, id);
		assertTrue( "array of proxies", g.getProxyArray().length==2 );
		assertTrue( "array of proxies", g.getProxyArray()[0]!=null );
		assertTrue("deferred load test",g.getProxyArray()[1].getProxyArray()[0]==null );
		assertTrue("deferred load test",g.getProxyArray()[1].getProxyArray()[2]==g );
		assertTrue( "set of proxies", g.getProxySet().size()==2 );
		Iterator iter = s.createQuery( "from Glarch g" ).iterate();
		while ( iter.hasNext() ) {
			iter.next();
			iter.remove();
		}
		s.getTransaction().commit();
		s.disconnect();
		SerializationHelper.deserialize( SerializationHelper.serialize(s) );
		s.close();
	}
