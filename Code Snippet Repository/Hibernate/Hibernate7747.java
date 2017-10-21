	@Test
	public void testAutoClear() {
		listener.callCount = 0;

		Session s = openSession();
		( (SessionImplementor) s ).setAutoClear( true );
		s.beginTransaction();
		assertEquals( 0, listener.callCount );
		s.getTransaction().commit();
		assertEquals( 1, listener.callCount );
		s.close();
		assertEquals( 1, listener.callCount );
	}
