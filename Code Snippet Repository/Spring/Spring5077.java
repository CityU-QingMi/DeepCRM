	@Test
	public void findMethodAnnotationOnBridgeMethod() throws Exception {
		Method bridgeMethod = SimpleFoo.class.getMethod("something", Object.class);
		assertTrue(bridgeMethod.isBridge());

		assertNull(bridgeMethod.getAnnotation(Order.class));
		assertNull(getAnnotation(bridgeMethod, Order.class));
		assertNotNull(findAnnotation(bridgeMethod, Order.class));

		// As of OpenJDK 8 b99, invoking getAnnotation() on a bridge method actually finds
		// an annotation on its 'bridged' method. This differs from the previous behavior
		// of JDK 5 through 7 and from the current behavior of the Eclipse compiler;
		// however, we need to ensure that the tests pass in the Gradle build. So we
		// comment out the following assertion.
		// assertNull(bridgeMethod.getAnnotation(Transactional.class));
		assertNotNull(getAnnotation(bridgeMethod, Transactional.class));
		assertNotNull(findAnnotation(bridgeMethod, Transactional.class));
	}
