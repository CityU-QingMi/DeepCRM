	@Test
	public void testLoadSnapshotWithCustomColumnReadAndWrite() {
		// Exercises entity snapshot load when select-before-update is true.
		Session s = openSession();
		Transaction t = s.beginTransaction();
		final double SIZE_IN_KB = 1536d;
		final double SIZE_IN_MB = SIZE_IN_KB / 1024d;
		Image image = new Image();
		image.setName( "picture.gif" );
		image.setSizeKb( SIZE_IN_KB );
		s.persist( image );
		s.flush();

		// Value returned by Oracle is a Types.NUMERIC, which is mapped to a BigDecimalType;
		// Cast returned value to Number then call Number.doubleValue() so it works on all dialects.
		Double sizeViaSql = ( (Number)s.createSQLQuery("select size_mb from image").uniqueResult() ).doubleValue();
		assertEquals(SIZE_IN_MB, sizeViaSql, 0.01d);
		t.commit();
		s.close();

		s = openSession();
		t = s.beginTransaction();
		final double NEW_SIZE_IN_KB = 2048d;
		final double NEW_SIZE_IN_MB = NEW_SIZE_IN_KB / 1024d;
		image.setSizeKb( NEW_SIZE_IN_KB );
		s.update( image );
		s.flush();

		sizeViaSql = ( (Number)s.createSQLQuery("select size_mb from image").uniqueResult() ).doubleValue();
		assertEquals(NEW_SIZE_IN_MB, sizeViaSql, 0.01d);

		s.delete(image);
		t.commit();
		s.close();
	}
