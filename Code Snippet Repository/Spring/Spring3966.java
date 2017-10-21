	@Test
	public void testScriptedClassThatDoesNotHaveANoArgCtor() throws Exception {
		ScriptSource script = mock(ScriptSource.class);
		String badScript = "class Foo { public Foo(String foo) {}}";
		given(script.getScriptAsString()).willReturn(badScript);
		given(script.suggestedClassName()).willReturn("someName");
		GroovyScriptFactory factory = new GroovyScriptFactory(ScriptFactoryPostProcessor.INLINE_SCRIPT_PREFIX
				+ badScript);
		try {
			factory.getScriptedObject(script);
			fail("Must have thrown a ScriptCompilationException (no public no-arg ctor in scripted class).");
		}
		catch (ScriptCompilationException expected) {
			assertTrue(expected.contains(NoSuchMethodException.class));
		}
	}
