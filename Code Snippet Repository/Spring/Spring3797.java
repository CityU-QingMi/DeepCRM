	@Test
	public void testCustomEnvironment() {
		JndiTemplateEditor je = new JndiTemplateEditor();
		// These properties are meaningless for JNDI, but we don't worry about that:
		// the underlying JNDI implementation will throw exceptions when the user tries
		// to look anything up
		je.setAsText("jndiInitialSomethingOrOther=org.springframework.myjndi.CompleteRubbish\nfoo=bar");
		JndiTemplate jt = (JndiTemplate) je.getValue();
		assertTrue(jt.getEnvironment().size() == 2);
		assertTrue(jt.getEnvironment().getProperty("jndiInitialSomethingOrOther").equals("org.springframework.myjndi.CompleteRubbish"));
		assertTrue(jt.getEnvironment().getProperty("foo").equals("bar"));
	}
