	@Test
	public void testCurrentSession() throws Exception {
		TestingJtaPlatformImpl.INSTANCE.getTransactionManager().begin();
		Session s = sessionFactory().getCurrentSession();
		Session s2 = sessionFactory().getCurrentSession();
		assertSame( s, s2 );
		TestingJtaPlatformImpl.INSTANCE.getTransactionManager().commit();
		assertFalse( s.isOpen() );

		// TODO : would be nice to automate-test that the SF internal map actually gets cleaned up
		//      i verified that is does currently in my debugger...
	}
