	@Test
	public void testRegisterAspectJAutoProxyCreator() throws Exception {
		AopNamespaceUtils.registerAspectJAutoProxyCreatorIfNecessary(this.parserContext, null);
		assertEquals("Incorrect number of definitions registered", 1, registry.getBeanDefinitionCount());

		AopNamespaceUtils.registerAspectJAutoProxyCreatorIfNecessary(this.parserContext, null);
		assertEquals("Incorrect number of definitions registered", 1, registry.getBeanDefinitionCount());

		BeanDefinition definition = registry.getBeanDefinition(AopConfigUtils.AUTO_PROXY_CREATOR_BEAN_NAME);
		assertEquals("Incorrect APC class",
				AspectJAwareAdvisorAutoProxyCreator.class.getName(), definition.getBeanClassName());
	}
