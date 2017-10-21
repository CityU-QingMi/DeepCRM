	@Test
	public final void verifyPetAndEmployee() {
		invocationCount.incrementAndGet();

		// Verifying dependency injection:
		assertNotNull("The pet field should have been autowired.", this.pet);

		// Verifying 'parameterized' support:
		Employee employee = this.applicationContext.getBean(this.employeeBeanName, Employee.class);
		assertEquals("Name of the employee configured as bean [" + this.employeeBeanName + "].", this.employeeName,
			employee.getName());
	}
