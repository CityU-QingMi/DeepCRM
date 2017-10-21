	@Test
	public void invokeMethod() throws Exception {
		String rob = "Rob Harrop";

		TestObject bean = new TestObject();
		bean.setName(rob);

		Method getName = TestObject.class.getMethod("getName", (Class[]) null);
		Method setName = TestObject.class.getMethod("setName", String.class);

		Object name = ReflectionUtils.invokeMethod(getName, bean);
		assertEquals("Incorrect name returned", rob, name);

		String juergen = "Juergen Hoeller";
		ReflectionUtils.invokeMethod(setName, bean, juergen);
		assertEquals("Incorrect name set", juergen, bean.getName());
	}
