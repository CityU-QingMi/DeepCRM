	@Test
	public void testGetScriptedObjectDoesNotChokeOnNullInterfacesBeingPassedIn() throws Exception {
		ScriptSource script = mock(ScriptSource.class);
		given(script.getScriptAsString()).willReturn("class Bar {}");
		given(script.suggestedClassName()).willReturn("someName");

		GroovyScriptFactory factory = new GroovyScriptFactory("a script source locator (doesn't matter here)");
		Object scriptedObject = factory.getScriptedObject(script);
		assertNotNull(scriptedObject);
	}
