	@Test
	@TestForIssue(jiraKey = "")
	public void testScrollableResults2() {
		final List params = new ArrayList();
		params.add( 1L );
		params.add( 2L );

		try (Session s = openSession()) {
			final Query query = s.createQuery( "from MyEntity e where e.id in (:ids)" )
					.setParameter( "ids", params )
					.setFetchSize( 10 );
			try (ScrollableResults scroll = query.scroll( )) {
				int i = 0;
				while ( scroll.next() ) {
					if ( i == 0 ) {
						assertThat( ((MyEntity) scroll.get()[0]).getDescription(), is( "entity_1" ) );
					}
					else {
						assertThat( ((MyEntity) scroll.get()[0]).getDescription(), is( "entity_2" ) );
					}
					i++;
				}
			}
		}
	}
