	@SkipForDialect(value = AbstractHANADialect.class, comment = "")
	@Test
	public void testImageTypeInSQLQuery() {
		Session s = openSession();
		Transaction t = s.beginTransaction();
		byte[] photo = buildLongByteArray( 15000, true );
		ImageHolder holder = new ImageHolder( photo );
		s.persist( holder );
		t.commit();
		s.close();

		s = openSession();
		t = s.beginTransaction();
		byte[] photoRead = ( byte[] ) s.createSQLQuery( getPhotosSQL() )
				.uniqueResult();
		assertTrue( Arrays.equals( photo, photoRead ) );
		s.delete( holder );
		t.commit();
		s.close();
	}
