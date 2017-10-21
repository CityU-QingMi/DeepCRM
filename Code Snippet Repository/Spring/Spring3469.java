	@Test
	public void testLoadingMultipleConfigFiles() {
		GenericGroovyApplicationContext ctx = new GenericGroovyApplicationContext(
				"org/springframework/context/groovy/applicationContext2.groovy",
				"org/springframework/context/groovy/applicationContext.groovy");

		Object framework = ctx.getBean("framework");
		assertNotNull("could not find framework bean", framework);
		assertEquals("Grails", framework);

		Object company = ctx.getBean("company");
		assertNotNull("could not find company bean", company);
		assertEquals("SpringSource", company);
	}
