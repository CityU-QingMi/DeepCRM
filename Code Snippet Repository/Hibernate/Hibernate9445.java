	@Test
	public void testImageProperty() {
		Session s = openSession();
		Transaction t = s.beginTransaction();
		byte[] photo = buildLongByteArray( 15000, true );
		ImageHolder holder = new ImageHolder( photo );
		s.save( holder );
		t.commit();
		s.close();

		s = openSession();
		t = s.beginTransaction();
		holder = ( ImageHolder ) s.get(  ImageHolder.class, holder.getId() );
		assertTrue( Arrays.equals( photo, holder.getPhoto() ) );
		photo = buildLongByteArray( 15000, false );
		holder.setPhoto( photo );
		s.save( holder );
		t.commit();
		s.close();

		s = openSession();
		t = s.beginTransaction();
		holder = ( ImageHolder ) s.get(  ImageHolder.class, holder.getId() );
		assertTrue( Arrays.equals( photo, holder.getPhoto() ) );
		s.delete( holder );
		t.commit();
		s.close();
	}
