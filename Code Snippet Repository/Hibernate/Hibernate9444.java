	@Test
	public void testTextProperty() {
		Session s = openSession();
		Transaction t = s.beginTransaction();
		String description = buildLongString( 15000, 'a' );
		TextHolder holder = new TextHolder( description );
		s.save( holder );
		t.commit();
		s.close();

		s = openSession();
		t = s.beginTransaction();
		holder = ( TextHolder ) s.get(  TextHolder.class, holder.getId() );
		assertEquals( description, holder.getDescription() );
		description = buildLongString( 15000, 'b' );
		holder.setDescription( description );
		s.save( holder );
		t.commit();
		s.close();

		s = openSession();
		t = s.beginTransaction();
		holder = ( TextHolder ) s.get(  TextHolder.class, holder.getId() );
		assertEquals( description, holder.getDescription() );
		s.delete( holder );
		t.commit();
		s.close();
	}
