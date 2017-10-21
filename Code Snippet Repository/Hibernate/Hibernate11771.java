	@Test
	public void testSupportsToMap() {
		final Cache<Object, Object> cache = region.getCache();
		final Iterator mock = Mockito.mock( Iterator.class );
		when( mock.hasNext() ).thenReturn( true ).thenReturn( false );
		when( mock.next() ).thenReturn( new Cache.Entry<Object, Object>() {
											@Override
											public Object getKey() {
												return "foo";
											}

											@Override
											public Object getValue() {
												return "bar";
											}

											@Override
											public <T> T unwrap(Class<T> clazz) {
												throw new UnsupportedOperationException( "Implement me!" );
											}
										} );
		when( cache.iterator() ).thenReturn( mock );
		final Map<String, String> map = region.toMap();
		assertThat( map.size(), is(1) );
		assertThat( map.get( "foo" ), equalTo( "bar" ));
	}
