	@Test
	public void testUnadvisedProxyCreationWithCallDuringConstructor() throws Exception {
		CglibTestBean target = new CglibTestBean();
		target.setName("Rob Harrop");

		AdvisedSupport pc = new AdvisedSupport();
		pc.setFrozen(true);
		pc.setTarget(target);

		CglibAopProxy aop = new CglibAopProxy(pc);
		CglibTestBean proxy = (CglibTestBean) aop.getProxy();
		assertNotNull("Proxy should not be null", proxy);
		assertEquals("Constructor overrode the value of name", "Rob Harrop", proxy.getName());
	}
