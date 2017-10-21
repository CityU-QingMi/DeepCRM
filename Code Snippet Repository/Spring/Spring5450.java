	@Test
	public void purchaserWithMethodInvoker() throws Exception {
		MethodInvoker methodInvoker = new MethodInvoker();
		methodInvoker.setTargetObject(new Greeter());
		methodInvoker.setTargetMethod("greet");
		methodInvoker.setArguments(new Object[] {new Purchaser()});
		methodInvoker.prepare();
		String greeting = (String) methodInvoker.invoke();
		assertEquals("purchaser: hello", greeting);
	}
