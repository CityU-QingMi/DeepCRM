	@Test
	public void stringWithMethodInvoker() throws Exception {
		MethodInvoker methodInvoker = new MethodInvoker();
		methodInvoker.setTargetObject(new Greeter());
		methodInvoker.setTargetMethod("greet");
		methodInvoker.setArguments(new Object[] {"no match"});

		exception.expect(NoSuchMethodException.class);
		methodInvoker.prepare();
	}
