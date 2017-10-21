	@Test
	public final void verifyAnnotationAutowiredAndInjectedFields() {
		assertNull("The nonrequiredLong field should NOT have been autowired.", this.nonrequiredLong);
		assertEquals("The quux field should have been autowired via @Autowired and @Qualifier.", "Quux", this.quux);
		assertEquals("The namedFoo field should have been injected via @Inject and @Named.", "Quux", this.namedQuux);
		assertSame("@Autowired/@Qualifier and @Inject/@Named quux should be the same object.", this.quux,
			this.namedQuux);

		assertNotNull("The pet field should have been autowired.", this.autowiredPet);
		assertNotNull("The pet field should have been injected.", this.injectedPet);
		assertEquals("Fido", this.autowiredPet.getName());
		assertEquals("Fido", this.injectedPet.getName());
		assertSame("@Autowired and @Inject pet should be the same object.", this.autowiredPet, this.injectedPet);
	}
