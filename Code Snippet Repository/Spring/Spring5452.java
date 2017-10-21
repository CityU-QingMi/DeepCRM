	@Test
	public void salesmanWithMethodInvoker() throws Exception {
		MethodInvoker methodInvoker = new MethodInvoker();
		methodInvoker.setTargetObject(new Greeter());
		methodInvoker.setTargetMethod("greet");
		methodInvoker.setArguments(new Object[] {new Salesman()});
		methodInvoker.prepare();
		String greeting = (String) methodInvoker.invoke();
		assertEquals("greetable: how are sales?", greeting);
	}
