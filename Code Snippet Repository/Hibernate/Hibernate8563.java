	@Test
	public void testEmbeddedCompositeID() throws Exception {
		Session s = openSession();
		s.beginTransaction();
		Location l = new Location();
		l.setCountryCode("AU");
		l.setDescription("foo bar");
		l.setLocale( Locale.getDefault() );
		l.setStreetName("Brunswick Rd");
		l.setStreetNumber(300);
		l.setCity("Melbourne");
		s.save(l);
		s.getTransaction().commit();
		s.close();

		s = openSession();
		s.beginTransaction();
		s.setFlushMode(FlushMode.MANUAL);
		l = (Location) s.createQuery( "from Location l where l.countryCode = 'AU' and l.description='foo bar'" )
				.list()
				.get(0);
		assertTrue( l.getCountryCode().equals("AU") );
		assertTrue( l.getCity().equals("Melbourne") );
		assertTrue( l.getLocale().equals( Locale.getDefault() ) );
		assertTrue( s.createCriteria(Location.class).add( Restrictions.eq( "streetNumber", new Integer(300) ) ).list().size()==1 );
		s.getTransaction().commit();
		s.close();

		s = openSession();
		s.beginTransaction();
		l.setDescription("sick're");
		s.update(l);
		s.getTransaction().commit();
		s.close();

		s = openSession();
		s.beginTransaction();
		l = new Location();
		l.setCountryCode("AU");
		l.setDescription("foo bar");
		l.setLocale(Locale.ENGLISH);
		l.setStreetName("Brunswick Rd");
		l.setStreetNumber(300);
		l.setCity("Melbourne");
		assertTrue( l==s.load(Location.class, l) );
		assertTrue( l.getLocale().equals( Locale.getDefault() ) );
		s.delete(l);
		s.getTransaction().commit();
		s.close();
	}
