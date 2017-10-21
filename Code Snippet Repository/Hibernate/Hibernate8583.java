	@Test
	public void testListIdentifiers() throws Exception {
		Session s = openSession();
		Transaction txn = s.beginTransaction();
		Fum fum = new Fum( fumKey("fum") );
		fum.setFum("fo fee fi");
		s.save(fum);
		fum = new Fum( fumKey("fi") );
		fum.setFum("fee fi fo");
		s.save(fum);
		List list = s.createQuery( "select fum.id from Fum as fum where not fum.fum='FRIEND'" ).list();
		assertTrue( "list identifiers", list.size()==2);
		Iterator iter = s.createQuery( "select fum.id from Fum fum where not fum.fum='FRIEND'" ).iterate();
		int i=0;
		while ( iter.hasNext() ) {
			assertTrue( "iterate identifiers",  iter.next() instanceof FumCompositeID);
			i++;
		}
		assertTrue(i==2);

		s.delete( s.load(Fum.class, (Serializable) list.get(0) ) );
		s.delete( s.load(Fum.class, (Serializable) list.get(1) ) );
		txn.commit();
		s.close();
	}
