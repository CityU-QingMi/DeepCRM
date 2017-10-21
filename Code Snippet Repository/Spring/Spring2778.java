	@Test
	public void testTargetReturnsThis() throws Throwable {
		// Test return value
		TestBean raw = new OwnSpouse();

		ProxyCreatorSupport pc = new ProxyCreatorSupport();
		pc.setInterfaces(new Class<?>[] {ITestBean.class});
		pc.setTarget(raw);

		ITestBean tb = (ITestBean) createProxy(pc);
		assertSame("this return is wrapped in proxy", tb, tb.getSpouse());
	}
