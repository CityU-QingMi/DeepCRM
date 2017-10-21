	@Test
	public void detectScriptTemplateConfigWithEngineName() {
		this.configurer.setEngineName("nashorn");
		this.configurer.setRenderObject("Template");
		this.configurer.setRenderFunction("render");

		DirectFieldAccessor accessor = new DirectFieldAccessor(this.view);
		this.view.setApplicationContext(this.wac);
		assertEquals("nashorn", accessor.getPropertyValue("engineName"));
		assertNotNull(accessor.getPropertyValue("engine"));
		assertEquals("Template", accessor.getPropertyValue("renderObject"));
		assertEquals("render", accessor.getPropertyValue("renderFunction"));
		assertEquals(MediaType.TEXT_HTML_VALUE, accessor.getPropertyValue("contentType"));
		assertEquals(StandardCharsets.UTF_8, accessor.getPropertyValue("charset"));
	}
