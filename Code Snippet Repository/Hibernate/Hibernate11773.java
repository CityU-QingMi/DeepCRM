	@Test
	public void testQueryCacheInvalidation() throws Exception {
		Session s = sessionFactory().openSession();
		Transaction t = s.beginTransaction();
		Item i = new Item();
		i.setName( "widget" );
		i.setDescription( "A really top-quality, full-featured widget." );
		s.persist( i );
		t.commit();
		s.close();

		SecondLevelCacheStatistics slcs = sessionFactory()
				.getStatistics()
				.getSecondLevelCacheStatistics( REGION_PREFIX + Item.class.getName() );

		assertThat( slcs.getPutCount(), equalTo( 1L ) );
		assertThat( slcs.getEntries().size(), equalTo( 1 ) );

		s = sessionFactory().openSession();
		t = s.beginTransaction();
		i = (Item) s.get( Item.class, i.getId() );

		assertThat( slcs.getHitCount(), equalTo( 1L ) );
		assertThat( slcs.getMissCount(), equalTo( 0L ) );

		i.setDescription( "A bog standard item" );

		t.commit();
		s.close();

		assertThat( slcs.getPutCount(), equalTo( 2L ) );

		Object entry = slcs.getEntries().get( i.getId() );
		Map map;
		if ( entry instanceof Map ) {
			map = (Map) entry;
		}
		else {
			map = ItemValueExtractor.getValue( entry );
		}
		assertThat( (String) map.get( "description" ), equalTo( "A bog standard item" ) );
		assertThat( (String) map.get( "name" ), equalTo( "widget" ) );

		// cleanup
		s = sessionFactory().openSession();
		t = s.beginTransaction();
		s.delete( i );
		t.commit();
		s.close();
	}
