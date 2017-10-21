	@Test
	public void findMethodAnnotationOnBridgedMethod() throws Exception {
		Method bridgedMethod = SimpleFoo.class.getMethod("something", String.class);
		assertFalse(bridgedMethod.isBridge());

		assertNull(bridgedMethod.getAnnotation(Order.class));
		assertNull(getAnnotation(bridgedMethod, Order.class));
		// AnnotationUtils.findAnnotation(Method, Class<A>) will not find an annotation on
		// the bridge method for a bridged method.
		assertNull(findAnnotation(bridgedMethod, Order.class));

		assertNotNull(bridgedMethod.getAnnotation(Transactional.class));
		assertNotNull(getAnnotation(bridgedMethod, Transactional.class));
		assertNotNull(findAnnotation(bridgedMethod, Transactional.class));
	}
