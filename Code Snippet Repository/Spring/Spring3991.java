	@Test
	public void testRefreshedScriptReferencePropagatesToCollaborators() throws Exception {
		BeanDefinition processorBeanDefinition = createScriptFactoryPostProcessor(true);
		BeanDefinition scriptedBeanDefinition = createScriptedGroovyBean();
		BeanDefinitionBuilder collaboratorBuilder = BeanDefinitionBuilder.rootBeanDefinition(DefaultMessengerService.class);
		collaboratorBuilder.addPropertyReference(MESSENGER_BEAN_NAME, MESSENGER_BEAN_NAME);

		GenericApplicationContext ctx = new GenericApplicationContext();
		ctx.registerBeanDefinition(PROCESSOR_BEAN_NAME, processorBeanDefinition);
		ctx.registerBeanDefinition(MESSENGER_BEAN_NAME, scriptedBeanDefinition);
		final String collaboratorBeanName = "collaborator";
		ctx.registerBeanDefinition(collaboratorBeanName, collaboratorBuilder.getBeanDefinition());
		ctx.refresh();

		Messenger messenger = (Messenger) ctx.getBean(MESSENGER_BEAN_NAME);
		assertEquals(MESSAGE_TEXT, messenger.getMessage());
		// cool; now let's change the script and check the refresh behaviour...
		pauseToLetRefreshDelayKickIn(DEFAULT_SECONDS_TO_PAUSE);
		StaticScriptSource source = getScriptSource(ctx);
		source.setScript(CHANGED_SCRIPT);
		Messenger refreshedMessenger = (Messenger) ctx.getBean(MESSENGER_BEAN_NAME);
		// the updated script surrounds the message in quotes before returning...
		assertEquals(EXPECTED_CHANGED_MESSAGE_TEXT, refreshedMessenger.getMessage());
		// ok, is this change reflected in the reference that the collaborator has?
		DefaultMessengerService collaborator = (DefaultMessengerService) ctx.getBean(collaboratorBeanName);
		assertEquals(EXPECTED_CHANGED_MESSAGE_TEXT, collaborator.getMessage());
	}
