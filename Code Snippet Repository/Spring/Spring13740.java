	@Test
	public void customEngineAndRenderFunction() throws Exception {
		ScriptEngine engine = mock(InvocableScriptEngine.class);
		given(engine.get("key")).willReturn("value");
		this.view.setEngine(engine);
		this.view.setRenderFunction("render");
		this.view.setApplicationContext(this.wac);
		engine = this.view.getEngine();
		assertNotNull(engine);
		assertEquals("value", engine.get("key"));
		DirectFieldAccessor accessor = new DirectFieldAccessor(this.view);
		assertNull(accessor.getPropertyValue("renderObject"));
		assertEquals("render", accessor.getPropertyValue("renderFunction"));
		assertEquals(StandardCharsets.UTF_8, accessor.getPropertyValue("charset"));
	}
