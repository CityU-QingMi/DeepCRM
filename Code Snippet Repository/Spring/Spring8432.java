	@Test
	@SuppressWarnings("")
	public void verifyScriptUsingGenericGroovyApplicationContext() {
		ApplicationContext ctx = new GenericGroovyApplicationContext(getClass(), "context.groovy");

		String foo = ctx.getBean("foo", String.class);
		assertEquals("Foo", foo);

		String bar = ctx.getBean("bar", String.class);
		assertEquals("Bar", bar);

		Pet pet = ctx.getBean(Pet.class);
		assertNotNull("pet", pet);
		assertEquals("Dogbert", pet.getName());

		Employee employee = ctx.getBean(Employee.class);
		assertNotNull("employee", employee);
		assertEquals("Dilbert", employee.getName());
		assertEquals("???", employee.getCompany());
	}
