	@Test
	public void detectScriptTemplateConfigWithEngine() {
		InvocableScriptEngine engine = mock(InvocableScriptEngine.class);
		this.configurer.setEngine(engine);
		this.configurer.setRenderObject("Template");
		this.configurer.setRenderFunction("render");
		this.configurer.setContentType(MediaType.TEXT_PLAIN_VALUE);
		this.configurer.setCharset(StandardCharsets.ISO_8859_1);
		this.configurer.setSharedEngine(true);

		DirectFieldAccessor accessor = new DirectFieldAccessor(this.view);
		this.view.setApplicationContext(this.wac);
		assertEquals(engine, accessor.getPropertyValue("engine"));
		assertEquals("Template", accessor.getPropertyValue("renderObject"));
		assertEquals("render", accessor.getPropertyValue("renderFunction"));
		assertEquals(MediaType.TEXT_PLAIN_VALUE, accessor.getPropertyValue("contentType"));
		assertEquals(StandardCharsets.ISO_8859_1, accessor.getPropertyValue("charset"));
		assertEquals(true, accessor.getPropertyValue("sharedEngine"));
	}
