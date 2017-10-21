	@Test
	public void scriptThatCompilesButIsJustPlainBad() throws Exception {
		ScriptSource script = mock(ScriptSource.class);
		final String badScript = "String getMessage() { throw new IllegalArgumentException(); }";
		given(script.getScriptAsString()).willReturn(badScript);
		given(script.isModified()).willReturn(true);
		BshScriptFactory factory = new BshScriptFactory(
				ScriptFactoryPostProcessor.INLINE_SCRIPT_PREFIX + badScript, Messenger.class);
		try {
			Messenger messenger = (Messenger) factory.getScriptedObject(script, Messenger.class);
			messenger.getMessage();
			fail("Must have thrown a BshScriptUtils.BshExecutionException.");
		}
		catch (BshScriptUtils.BshExecutionException expected) {
		}
	}
