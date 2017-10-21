	@Test
	public void clearTransactional() {
		Cache target = new ConcurrentMapCache("testCache");
		Cache cache = new TransactionAwareCacheDecorator(target);
		Object key = new Object();
		cache.put(key, "123");


		TransactionStatus status = this.txManager.getTransaction(
				new DefaultTransactionAttribute(TransactionDefinition.PROPAGATION_REQUIRED));
		cache.clear();
		assertEquals("123", target.get(key, String.class));
		this.txManager.commit(status);

		assertNull(target.get(key));
	}
