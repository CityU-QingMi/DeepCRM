	@Test
	public void testMultipleCachingEval() throws Exception {
		AnnotatedClass target = new AnnotatedClass();
		Method method = ReflectionUtils.findMethod(AnnotatedClass.class, "multipleCaching", Object.class,
				Object.class);
		Object[] args = new Object[] { new Object(), new Object() };
		Collection<ConcurrentMapCache> caches = Collections.singleton(new ConcurrentMapCache("test"));

		EvaluationContext evalCtx = this.eval.createEvaluationContext(caches, method, args,
				target, target.getClass(), null);
		Collection<CacheOperation> ops = getOps("multipleCaching");

		Iterator<CacheOperation> it = ops.iterator();

		AnnotatedElementKey key = new AnnotatedElementKey(method, AnnotatedClass.class);

		Object keyA = this.eval.key(it.next().getKey(), key, evalCtx);
		Object keyB = this.eval.key(it.next().getKey(), key, evalCtx);

		assertEquals(args[0], keyA);
		assertEquals(args[1], keyB);
	}
