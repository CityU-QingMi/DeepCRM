	@Test
	public void customerWithMethodInvoker() throws Exception {
		MethodInvoker methodInvoker = new MethodInvoker();
		methodInvoker.setTargetObject(new Greeter());
		methodInvoker.setTargetMethod("greet");
		methodInvoker.setArguments(new Object[] {new Customer()});
		methodInvoker.prepare();
		String greeting = (String) methodInvoker.invoke();
		assertEquals("customer: good day", greeting);
	}
