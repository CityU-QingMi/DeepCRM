	@Test
	public void testRegisterAspectJAutoProxyCreatorWithExistingAutoProxyCreator() throws Exception {
		AopNamespaceUtils.registerAutoProxyCreatorIfNecessary(this.parserContext, null);
		assertEquals(1, registry.getBeanDefinitionCount());

		AopNamespaceUtils.registerAspectJAutoProxyCreatorIfNecessary(this.parserContext, null);
		assertEquals("Incorrect definition count", 1, registry.getBeanDefinitionCount());

		BeanDefinition definition = registry.getBeanDefinition(AopConfigUtils.AUTO_PROXY_CREATOR_BEAN_NAME);
		assertEquals("APC class not switched",
				AspectJAwareAdvisorAutoProxyCreator.class.getName(), definition.getBeanClassName());
	}
