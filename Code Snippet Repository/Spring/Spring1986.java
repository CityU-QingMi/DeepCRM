	@Test
	public void evictTransactional() {
		Cache target = new ConcurrentMapCache("testCache");
		Cache cache = new TransactionAwareCacheDecorator(target);
		Object key = new Object();
		cache.put(key, "123");


		TransactionStatus status = this.txManager.getTransaction(
				new DefaultTransactionAttribute(TransactionDefinition.PROPAGATION_REQUIRED));
		cache.evict(key);
		assertEquals("123", target.get(key, String.class));
		this.txManager.commit(status);

		assertNull(target.get(key));
	}
