	@Test
	public void retrieveAttributes() throws Exception {
		WebSession session = new InMemoryWebSessionStore().createWebSession().block(Duration.ZERO);
		assertNotNull(session);

		session.getAttributes().put("attr1", "value1");
		session.getAttributes().put("attr2", "value2");
		session.getAttributes().put("attr3", new TestBean());
		session.getAttributes().put("attr4", new TestBean());

		assertEquals("Named attributes (attr1, attr2) should be 'known' right away",
				new HashSet<>(asList("attr1", "attr2")),
				sessionAttributesHandler.retrieveAttributes(session).keySet());

		// Resolve 'attr3' by type
		sessionAttributesHandler.isHandlerSessionAttribute("attr3", TestBean.class);

		assertEquals("Named attributes (attr1, attr2) and resolved attribute (att3) should be 'known'",
				new HashSet<>(asList("attr1", "attr2", "attr3")),
				sessionAttributesHandler.retrieveAttributes(session).keySet());
	}
