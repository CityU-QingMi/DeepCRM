	@Test
	public void testReadOnlyOnTextType() {
		final String origText = "some huge text string";
		final String newText = "some even bigger text string";

		Session s = openSession();
		s.beginTransaction();
		s.setCacheMode( CacheMode.IGNORE );
		TextHolder holder = new TextHolder( origText );
		s.save( holder );
		Long id = holder.getId();
		s.getTransaction().commit();
		s.close();

		s = openSession();
		s.beginTransaction();
		s.setDefaultReadOnly( true );
		s.setCacheMode( CacheMode.IGNORE );
		holder = ( TextHolder ) s.get( TextHolder.class, id );
		s.setDefaultReadOnly( false );
		holder.setTheText( newText );
		s.flush();
		s.getTransaction().commit();
		s.close();

		s = openSession();
		s.beginTransaction();
		holder = ( TextHolder ) s.get( TextHolder.class, id );
		assertEquals( "change written to database", origText, holder.getTheText() );
		s.delete( holder );
		s.getTransaction().commit();
		s.close();
	}
