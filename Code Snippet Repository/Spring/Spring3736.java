	@Test
	public void testWithCglibProxy() throws Exception {
		IJmxTestBean tb = createJmxTestBean();
		ProxyFactory pf = new ProxyFactory();
		pf.setTarget(tb);
		pf.addAdvice(new NopInterceptor());
		Object proxy = pf.getProxy();

		MetadataMBeanInfoAssembler assembler = (MetadataMBeanInfoAssembler) getAssembler();

		MBeanExporter exporter = new MBeanExporter();
		exporter.setBeanFactory(getContext());
		exporter.setAssembler(assembler);

		String objectName = "spring:bean=test,proxy=true";

		Map<String, Object> beans = new HashMap<>();
		beans.put(objectName, proxy);
		exporter.setBeans(beans);
		start(exporter);

		MBeanInfo inf = getServer().getMBeanInfo(ObjectNameManager.getInstance(objectName));
		assertEquals("Incorrect number of operations", getExpectedOperationCount(), inf.getOperations().length);
		assertEquals("Incorrect number of attributes", getExpectedAttributeCount(), inf.getAttributes().length);

		assertTrue("Not included in autodetection", assembler.includeBean(proxy.getClass(), "some bean name"));
	}
