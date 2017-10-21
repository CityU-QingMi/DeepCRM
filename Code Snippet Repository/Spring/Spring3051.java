	@Test
	public void multipleStereotypes() throws Exception {
		Collection<CacheOperation> ops = getOps(AnnotatedClass.class, "multipleStereotype", 3);
		Iterator<CacheOperation> it = ops.iterator();
		assertTrue(it.next() instanceof CacheableOperation);
		CacheOperation next = it.next();
		assertTrue(next instanceof CacheEvictOperation);
		assertTrue(next.getCacheNames().contains("foo"));
		next = it.next();
		assertTrue(next instanceof CacheEvictOperation);
		assertTrue(next.getCacheNames().contains("bar"));
	}
