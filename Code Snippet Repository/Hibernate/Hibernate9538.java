	@Test
	public void testDropDuringActiveJtaTransaction() throws Exception {
		final SessionFactory sessionFactory = buildSessionFactory();

		JtaPlatformStandardTestingImpl.INSTANCE.transactionManager().begin();
		try {
			sessionFactory.close();
		}
		finally {
			JtaPlatformStandardTestingImpl.INSTANCE.transactionManager().commit();
		}
	}
