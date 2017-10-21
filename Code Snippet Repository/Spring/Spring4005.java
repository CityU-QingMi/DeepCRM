	@Test
	public void testAopCglibProxy() throws Exception {
		ModelMap map = new ModelMap();
		ProxyFactory factory = new ProxyFactory();
		SomeInnerClass val = new SomeInnerClass();
		factory.setTarget(val);
		factory.setProxyTargetClass(true);
		map.addAttribute(factory.getProxy());
		assertTrue(map.containsKey("someInnerClass"));
		assertEquals(val, map.get("someInnerClass"));
	}
