	@Test
	@RequiresDialectFeature( DialectChecks.SupportsExpectedLobUsagePattern.class )
	public void testParameterizedType() throws Exception {
		Session s;
		Transaction tx;
		s = openSession();
		tx = s.beginTransaction();
		Forest f = new Forest();
		f.setSmallText( "ThisIsASmallText" );
		f.setBigText( "ThisIsABigText" );
		s.persist( f );
		tx.commit();
		s.close();
		s = openSession();
		tx = s.beginTransaction();
		Forest f2 = (Forest) s.get( Forest.class, f.getId() );
		assertEquals( f.getSmallText().toLowerCase(Locale.ROOT), f2.getSmallText() );
		assertEquals( f.getBigText().toUpperCase(Locale.ROOT), f2.getBigText() );
		tx.commit();
		s.close();
	}
