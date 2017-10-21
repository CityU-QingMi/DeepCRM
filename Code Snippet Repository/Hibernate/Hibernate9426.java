	@Test
	public void testSetParameters() {
		final List params = new ArrayList();
		params.add( new BigInteger( "2" ) );
		params.add( new BigInteger( "3" ) );

		try (Session s = openSession()) {
			final Query query = s.createNativeQuery( "select e.big from MY_ENTITY e where e.big in (:bigValues)" )
					.setParameter( "bigValues", params );
			try (ScrollableResults scroll = query.scroll()) {
				while ( scroll.next() ) {
					assertThat( scroll.get()[0], not( nullValue()) );
				}
			}
		}
	}
