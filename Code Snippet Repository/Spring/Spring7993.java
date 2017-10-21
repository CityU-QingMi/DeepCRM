	@Test
	public void testValidUsage() {
		Object o = new Object();

		EntityManager mockEm = mock(EntityManager.class);
		given(mockEm.isOpen()).willReturn(true);

		EntityManagerFactory mockEmf = mock(EntityManagerFactory.class);
		given(mockEmf.createEntityManager()).willReturn(mockEm);

		SharedEntityManagerBean proxyFactoryBean = new SharedEntityManagerBean();
		proxyFactoryBean.setEntityManagerFactory(mockEmf);
		proxyFactoryBean.afterPropertiesSet();

		assertTrue(EntityManager.class.isAssignableFrom(proxyFactoryBean.getObjectType()));
		assertTrue(proxyFactoryBean.isSingleton());

		EntityManager proxy = proxyFactoryBean.getObject();
		assertSame(proxy, proxyFactoryBean.getObject());
		assertFalse(proxy.contains(o));

		assertTrue(proxy instanceof EntityManagerProxy);
		EntityManagerProxy emProxy = (EntityManagerProxy) proxy;
		try {
			emProxy.getTargetEntityManager();
			fail("Should have thrown IllegalStateException outside of transaction");
		}
		catch (IllegalStateException ex) {
			// expected
		}

		TransactionSynchronizationManager.bindResource(mockEmf, new EntityManagerHolder(mockEm));
		try {
			assertSame(mockEm, emProxy.getTargetEntityManager());
		}
		finally {
			TransactionSynchronizationManager.unbindResource(mockEmf);
		}

		assertTrue(TransactionSynchronizationManager.getResourceMap().isEmpty());
		verify(mockEm).contains(o);
		verify(mockEm).close();
	}
