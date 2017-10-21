	@Test
	void findAnnotatedMethodsDelegates() throws Throwable {
		assertEquals(
			AnnotationUtils.findAnnotatedMethods(Probe.class, Tag.class,
				ReflectionUtils.HierarchyTraversalMode.TOP_DOWN),
			AnnotationSupport.findAnnotatedMethods(Probe.class, Tag.class, HierarchyTraversalMode.TOP_DOWN));
		assertEquals(
			AnnotationUtils.findAnnotatedMethods(Probe.class, Tag.class,
				ReflectionUtils.HierarchyTraversalMode.BOTTOM_UP),
			AnnotationSupport.findAnnotatedMethods(Probe.class, Tag.class, HierarchyTraversalMode.BOTTOM_UP));

		assertEquals(
			AnnotationUtils.findAnnotatedMethods(Probe.class, Override.class,
				ReflectionUtils.HierarchyTraversalMode.TOP_DOWN),
			AnnotationSupport.findAnnotatedMethods(Probe.class, Override.class, HierarchyTraversalMode.TOP_DOWN));
		assertEquals(
			AnnotationUtils.findAnnotatedMethods(Probe.class, Override.class,
				ReflectionUtils.HierarchyTraversalMode.BOTTOM_UP),
			AnnotationSupport.findAnnotatedMethods(Probe.class, Override.class, HierarchyTraversalMode.BOTTOM_UP));
	}
