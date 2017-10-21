	@Test
	public void testReplaceArgument() throws Throwable {
		TestBean tb = new TestBean();
		ProxyFactory pc = new ProxyFactory();
		pc.addInterface(ITestBean.class);
		pc.setTarget(tb);
		pc.addAdvisor(new StringSetterNullReplacementAdvice());

		ITestBean t = (ITestBean) pc.getProxy();
		int newAge = 5;
		t.setAge(newAge);
		assertEquals(newAge, t.getAge());
		String newName = "greg";
		t.setName(newName);
		assertEquals(newName, t.getName());

		t.setName(null);
		// Null replacement magic should work
		assertEquals("", t.getName());
	}
