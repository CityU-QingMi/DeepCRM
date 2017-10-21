	@Test
	public void putTransactional() {
		Cache target = new ConcurrentMapCache("testCache");
		Cache cache = new TransactionAwareCacheDecorator(target);

		TransactionStatus status = this.txManager.getTransaction(
				new DefaultTransactionAttribute(TransactionDefinition.PROPAGATION_REQUIRED));

		Object key = new Object();
		cache.put(key, "123");
		assertNull(target.get(key));
		this.txManager.commit(status);

		assertEquals("123", target.get(key, String.class));
	}
