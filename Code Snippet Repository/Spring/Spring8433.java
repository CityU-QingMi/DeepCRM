	@Test
	public final void verifyAnnotationAutowiredFields() {
		assertNotNull("The employee field should have been autowired.", this.employee);
		assertEquals("Dilbert", this.employee.getName());

		assertNotNull("The pet field should have been autowired.", this.pet);
		assertEquals("Dogbert", this.pet.getName());

		assertEquals("The foo field should have been autowired.", "Groovy Foo", this.foo);
		assertEquals("The bar field should have been autowired.", "XML Bar", this.bar);
	}
