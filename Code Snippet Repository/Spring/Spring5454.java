	@Test
	public void regularWithMethodInvoker() throws Exception {
		MethodInvoker methodInvoker = new MethodInvoker();
		methodInvoker.setTargetObject(new Greeter());
		methodInvoker.setTargetMethod("greet");
		methodInvoker.setArguments(new Object[] {new Regular("Kotter")});
		methodInvoker.prepare();
		String greeting = (String) methodInvoker.invoke();
		assertEquals("regular: welcome back Kotter", greeting);
	}
