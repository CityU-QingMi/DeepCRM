	@Test
	public void testCanUnwrapAopProxy() {
		EntityManager em = entityManagerFactory.createEntityManager();
		EntityManager proxy = ProxyFactory.getProxy(EntityManager.class, new SingletonTargetSource(em));
		assertTrue(em instanceof org.hibernate.jpa.HibernateEntityManager);
		assertFalse(proxy instanceof org.hibernate.jpa.HibernateEntityManager);
		assertTrue(proxy.unwrap(org.hibernate.jpa.HibernateEntityManager.class) != null);
		assertSame(em, proxy.unwrap(org.hibernate.jpa.HibernateEntityManager.class));
		assertSame(em.getDelegate(), proxy.getDelegate());
	}
