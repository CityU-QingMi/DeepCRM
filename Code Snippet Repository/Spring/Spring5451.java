	@Test
	public void shopperWithMethodInvoker() throws Exception {
		MethodInvoker methodInvoker = new MethodInvoker();
		methodInvoker.setTargetObject(new Greeter());
		methodInvoker.setTargetMethod("greet");
		methodInvoker.setArguments(new Object[] {new Shopper()});
		methodInvoker.prepare();
		String greeting = (String) methodInvoker.invoke();
		assertEquals("purchaser: may I help you?", greeting);
	}
