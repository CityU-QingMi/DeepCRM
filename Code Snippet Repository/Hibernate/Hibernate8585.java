	@Test
	public void testCompositeIDOneToOne() throws Exception {
		Session s = openSession();
		Transaction txn = s.beginTransaction();
		FumCompositeID fumKey = fumKey("fum");
		Fum fum = new Fum( fumKey );
		fum.setFum("fee fi fo");
		//s.save(fum);
		Fumm fumm = new Fumm();
		fumm.setFum(fum);
		s.save(fumm);
		txn.commit();
		s.close();

		s = openSession();
		txn = s.beginTransaction();
		fumm = (Fumm) s.load( Fumm.class, fumKey );
		//s.delete( fumm.getFum() );
		s.delete(fumm);
		txn.commit();
		s.close();
	}
