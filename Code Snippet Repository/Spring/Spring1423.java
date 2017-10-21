	@Test
	public void testInvokeWithIntArgument() throws Exception {
		ArgumentConvertingMethodInvoker methodInvoker = new ArgumentConvertingMethodInvoker();
		methodInvoker.setTargetClass(TestClass1.class);
		methodInvoker.setTargetMethod("intArgument");
		methodInvoker.setArguments(5);
		methodInvoker.prepare();
		methodInvoker.invoke();

		methodInvoker = new ArgumentConvertingMethodInvoker();
		methodInvoker.setTargetClass(TestClass1.class);
		methodInvoker.setTargetMethod("intArgument");
		methodInvoker.setArguments(5);
		methodInvoker.prepare();
		methodInvoker.invoke();
	}
