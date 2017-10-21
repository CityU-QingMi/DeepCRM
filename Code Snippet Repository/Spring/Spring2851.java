	@Test
	public void testEqualsAndHashCodeDefined() throws Exception {
		AdvisedSupport as = new AdvisedSupport(Named.class);
		as.setTarget(new Person());
		JdkDynamicAopProxy aopProxy = new JdkDynamicAopProxy(as);
		Named proxy = (Named) aopProxy.getProxy();
		Named named = new Person();
		assertEquals("equals()", proxy, named);
		assertEquals("hashCode()", proxy.hashCode(), named.hashCode());
	}
