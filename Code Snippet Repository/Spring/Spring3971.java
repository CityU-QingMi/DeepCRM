	@Test
	public void testGetScriptedObjectDoesChokeOnNullScriptSourceBeingPassedIn() throws Exception {
		GroovyScriptFactory factory = new GroovyScriptFactory("a script source locator (doesn't matter here)");
		try {
			factory.getScriptedObject(null);
			fail("Must have thrown a NullPointerException as per contract ('null' ScriptSource supplied");
		}
		catch (NullPointerException expected) {
		}
	}
