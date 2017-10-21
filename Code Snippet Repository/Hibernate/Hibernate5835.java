	@Test
	public void testPassingSynchronizationType() {
		try {
			entityManagerFactory().createEntityManager( SynchronizationType.SYNCHRONIZED );
			fail( "Call should have throw ISE" );
		}
		catch (IllegalStateException expected) {
		}

		try {
			entityManagerFactory().createEntityManager( SynchronizationType.UNSYNCHRONIZED );
			fail( "Call should have throw ISE" );
		}
		catch (IllegalStateException expected) {
		}

		try {
			entityManagerFactory().createEntityManager( SynchronizationType.SYNCHRONIZED, Collections.emptyMap() );
			fail( "Call should have throw ISE" );
		}
		catch (IllegalStateException expected) {
		}

		try {
			entityManagerFactory().createEntityManager( SynchronizationType.UNSYNCHRONIZED, Collections.emptyMap() );
			fail( "Call should have throw ISE" );
		}
		catch (IllegalStateException expected) {
		}
	}
