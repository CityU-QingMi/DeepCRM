	@Test
	public void vipWithMethodInvoker() throws Exception {
		MethodInvoker methodInvoker = new MethodInvoker();
		methodInvoker.setTargetObject(new Greeter());
		methodInvoker.setTargetMethod("greet");
		methodInvoker.setArguments(new Object[] {new VIP("Fonzie")});
		methodInvoker.prepare();
		String greeting = (String) methodInvoker.invoke();
		assertEquals("regular: whassup dude?", greeting);
	}
