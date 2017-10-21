	@Test
	public void testMultipleScanCalls() {
		GenericApplicationContext context = new GenericApplicationContext();
		ClassPathBeanDefinitionScanner scanner = new ClassPathBeanDefinitionScanner(context);
		int initialBeanCount = context.getBeanDefinitionCount();
		int scannedBeanCount = scanner.scan(BASE_PACKAGE);
		assertEquals(12, scannedBeanCount);
		assertEquals(scannedBeanCount, context.getBeanDefinitionCount() - initialBeanCount);
		int addedBeanCount = scanner.scan("org.springframework.aop.aspectj.annotation");
		assertEquals(initialBeanCount + scannedBeanCount + addedBeanCount, context.getBeanDefinitionCount());
	}
