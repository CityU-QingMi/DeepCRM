	@Test
	public void testServletContextAttributeExporter() {
		TestBean tb = new TestBean();
		Map<String, Object> attributes = new HashMap<>();
		attributes.put("attr1", "value1");
		attributes.put("attr2", tb);

		MockServletContext sc = new MockServletContext();
		ServletContextAttributeExporter exporter = new ServletContextAttributeExporter();
		exporter.setAttributes(attributes);
		exporter.setServletContext(sc);

		assertEquals("value1", sc.getAttribute("attr1"));
		assertSame(tb, sc.getAttribute("attr2"));
	}
