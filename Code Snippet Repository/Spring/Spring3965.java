	@Test
	public void testScriptCompilationException() throws Exception {
		try {
			new ClassPathXmlApplicationContext("org/springframework/scripting/groovy/groovyBrokenContext.xml");
			fail("Should throw exception for broken script file");
		}
		catch (NestedRuntimeException ex) {
			assertTrue("Wrong root cause: " + ex, ex.contains(ScriptCompilationException.class));
		}
	}
