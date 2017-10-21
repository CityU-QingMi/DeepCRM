	@Test
	public void testLoadingMultipleConfigFilesWithRelativeClass() {
		GenericGroovyApplicationContext ctx = new GenericGroovyApplicationContext();
		ctx.load(GroovyApplicationContextTests.class, "applicationContext2.groovy", "applicationContext.groovy");
		ctx.refresh();

		Object framework = ctx.getBean("framework");
		assertNotNull("could not find framework bean", framework);
		assertEquals("Grails", framework);

		Object company = ctx.getBean("company");
		assertNotNull("could not find company bean", company);
		assertEquals("SpringSource", company);
	}
