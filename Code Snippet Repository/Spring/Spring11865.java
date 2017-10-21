	@Test
	public void cleanupAttributes() throws Exception {
		WebSession session = new InMemoryWebSessionStore().createWebSession().block(Duration.ZERO);
		assertNotNull(session);

		session.getAttributes().put("attr1", "value1");
		session.getAttributes().put("attr2", "value2");
		session.getAttributes().put("attr3", new TestBean());

		this.sessionAttributesHandler.cleanupAttributes(session);

		assertNull(session.getAttributes().get("attr1"));
		assertNull(session.getAttributes().get("attr2"));
		assertNotNull(session.getAttributes().get("attr3"));

		// Resolve 'attr3' by type
		this.sessionAttributesHandler.isHandlerSessionAttribute("attr3", TestBean.class);
		this.sessionAttributesHandler.cleanupAttributes(session);

		assertNull(session.getAttributes().get("attr3"));
	}
