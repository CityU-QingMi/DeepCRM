	@Test
	public void testCallbacks() {
		// test pre-assertions
		assert observer.closingCount == 0;
		assert observer.closedCount == 0;
		assertEquals( "observer not notified of creation", 1, observer.creationCount );
		assertEquals( "listener not notified of creation", 1, listener.initCount );

		sessionFactory().close();

		assertEquals( "observer not notified of closing", 1, observer.closingCount );
		assertEquals( "observer not notified of close", 1, observer.closedCount );
		assertEquals( "listener not notified of close", 1, listener.destoryCount );
	}
