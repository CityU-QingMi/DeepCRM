	@Test
	public void testComponentRegistration() {
		assertTrue("Parser should have registered a component named 'listener1'",
				context.containsComponentDefinition("listener1"));
		assertTrue("Parser should have registered a component named 'listener2'",
				context.containsComponentDefinition("listener2"));
		assertTrue("Parser should have registered a component named 'listener3'",
				context.containsComponentDefinition("listener3"));
		assertTrue("Parser should have registered a component named '"
				+ DefaultMessageListenerContainer.class.getName() + "#0'",
				context.containsComponentDefinition(DefaultMessageListenerContainer.class.getName() + "#0"));
		assertTrue("Parser should have registered a component named '"
				+ JmsMessageEndpointManager.class.getName() + "#0'",
				context.containsComponentDefinition(JmsMessageEndpointManager.class.getName() + "#0"));
		assertTrue("Parser should have registered a component named 'testJmsFactory",
				context.containsComponentDefinition("testJmsFactory"));
		assertTrue("Parser should have registered a component named 'testJcaFactory",
				context.containsComponentDefinition("testJcaFactory"));
		assertTrue("Parser should have registered a component named 'testJcaFactory",
				context.containsComponentDefinition("onlyJmsFactory"));
	}
