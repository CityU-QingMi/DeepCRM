	@Test
	public void testExplicitClear() {
		listener.callCount = 0;

		Session s = openSession();
		s.clear();
		assertEquals( 1, listener.callCount );
		s.close();
		assertEquals( 1, listener.callCount );
	}
