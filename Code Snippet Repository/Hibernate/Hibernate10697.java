	@Test
	public void testDataNotPersisted() throws Exception {
		TestingJtaPlatformImpl.INSTANCE.getTransactionManager().begin();

		try {
			// Checking if the entity became persisted
			EntityManager em = getEntityManager();
			long count = em.createQuery( "from StrTestEntity s where s.str = 'x'" ).getResultList().size();
			Assert.assertEquals( 0, count );
		}
		finally {
			TestingJtaPlatformImpl.tryCommit();
		}
	}
