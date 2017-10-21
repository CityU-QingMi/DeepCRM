	@Test
	public void scriptCompilationException() throws Exception {
		try {
			new ClassPathXmlApplicationContext("org/springframework/scripting/bsh/bshBrokenContext.xml");
			fail("Must throw exception for broken script file");
		}
		catch (NestedRuntimeException ex) {
			assertTrue(ex.contains(ScriptCompilationException.class));
		}
	}
