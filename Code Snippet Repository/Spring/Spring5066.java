	@Test
	public void findMethodAnnotationWithAnnotationOnMethodInInterface() throws Exception {
		Method m = Leaf.class.getMethod("fromInterfaceImplementedByRoot");
		// @Order is not @Inherited
		assertNull(m.getAnnotation(Order.class));
		// getAnnotation() does not search on interfaces
		assertNull(getAnnotation(m, Order.class));
		// findAnnotation() does search on interfaces
		assertNotNull(findAnnotation(m, Order.class));
	}
