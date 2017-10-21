	@Test
	public void storeAttributes() throws Exception {
		WebSession session = new InMemoryWebSessionStore().createWebSession().block(Duration.ZERO);
		assertNotNull(session);

		ModelMap model = new ModelMap();
		model.put("attr1", "value1");
		model.put("attr2", "value2");
		model.put("attr3", new TestBean());

		sessionAttributesHandler.storeAttributes(session, model);

		assertEquals("value1", session.getAttributes().get("attr1"));
		assertEquals("value2", session.getAttributes().get("attr2"));
		assertTrue(session.getAttributes().get("attr3") instanceof TestBean);
	}
