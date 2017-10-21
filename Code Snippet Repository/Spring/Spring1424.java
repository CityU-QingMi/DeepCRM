	@Test
	public void testInvokeWithIntArguments() throws Exception {
		MethodInvokingBean methodInvoker = new MethodInvokingBean();
		methodInvoker.setTargetClass(TestClass1.class);
		methodInvoker.setTargetMethod("intArguments");
		methodInvoker.setArguments(new Object[] {new Integer[] {5, 10}});
		methodInvoker.afterPropertiesSet();

		methodInvoker = new MethodInvokingBean();
		methodInvoker.setTargetClass(TestClass1.class);
		methodInvoker.setTargetMethod("intArguments");
		methodInvoker.setArguments(new Object[] {new String[] {"5", "10"}});
		methodInvoker.afterPropertiesSet();

		methodInvoker = new MethodInvokingBean();
		methodInvoker.setTargetClass(TestClass1.class);
		methodInvoker.setTargetMethod("intArguments");
		methodInvoker.setArguments(new Object[] {new Integer[] {5, 10}});
		methodInvoker.afterPropertiesSet();

		methodInvoker = new MethodInvokingBean();
		methodInvoker.setTargetClass(TestClass1.class);
		methodInvoker.setTargetMethod("intArguments");
		methodInvoker.setArguments("5", "10");
		methodInvoker.afterPropertiesSet();

		methodInvoker = new MethodInvokingBean();
		methodInvoker.setTargetClass(TestClass1.class);
		methodInvoker.setTargetMethod("intArguments");
		methodInvoker.setArguments(new Object[] {new Integer[] {5, 10}});
		methodInvoker.afterPropertiesSet();

		methodInvoker = new MethodInvokingBean();
		methodInvoker.setTargetClass(TestClass1.class);
		methodInvoker.setTargetMethod("intArguments");
		methodInvoker.setArguments("5", "10");
		methodInvoker.afterPropertiesSet();
	}
