	public void testBlobClob() throws Exception {
		Session s = openSession();
		s.beginTransaction();
		Blobber b = new Blobber();
		b.setBlob( s.getLobHelper().createBlob( "foo/bar/baz".getBytes() ) );
		b.setClob( s.getLobHelper().createClob("foo/bar/baz") );
		s.save(b);
		//s.refresh(b);
		//assertTrue( b.getClob() instanceof ClobImpl );
		s.flush();
		s.refresh(b);
		//b.getBlob().setBytes( 2, "abc".getBytes() );
        log.debug("levinson: just bfore b.getClob()");
        b.getClob().getSubString(2, 3);
		//b.getClob().setString(2, "abc");
		s.flush();
		s.getTransaction().commit();
		s.close();

		s = openSession();
		s.beginTransaction();
		b = (Blobber) s.load( Blobber.class, new Integer( b.getId() ) );
		Blobber b2 = new Blobber();
		s.save(b2);
		b2.setBlob( b.getBlob() );
		b.setBlob(null);
		//assertTrue( b.getClob().getSubString(1, 3).equals("fab") );
		b.getClob().getSubString(1, 6);
		//b.getClob().setString(1, "qwerty");
		s.flush();
		s.getTransaction().commit();
		s.close();

		s = openSession();
		s.beginTransaction();
		b = (Blobber) s.load( Blobber.class, new Integer( b.getId() ) );
		b.setClob( s.getLobHelper().createClob("xcvfxvc xcvbx cvbx cvbx cvbxcvbxcvbxcvb") );
		s.flush();
		s.getTransaction().commit();
		s.close();

		s = openSession();
		s.beginTransaction();
		b = (Blobber) s.load( Blobber.class, new Integer( b.getId() ) );
		assertTrue( b.getClob().getSubString(1, 7).equals("xcvfxvc") );
		//b.getClob().setString(5, "1234567890");
		s.flush();
		s.getTransaction().commit();
		s.close();
	}
