	@Test
	public void testRemoveNonExistingEntity() {
		MergeContext cache = new MergeContext( session, new DoNothingEntityCopyObserver() );
		try {
			cache.remove( new Simple( 1 ) );
		}
		catch (UnsupportedOperationException ex) {
			// expected; remove is not supported.
		}
	}
