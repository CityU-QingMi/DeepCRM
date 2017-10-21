	@Override
	protected AbstractBeanDefinition parseInternal(Element element, ParserContext parserContext) {
		String agentId = element.getAttribute(AGENT_ID_ATTRIBUTE);
		if (StringUtils.hasText(agentId)) {
			RootBeanDefinition bd = new RootBeanDefinition(MBeanServerFactoryBean.class);
			bd.getPropertyValues().add("agentId", agentId);
			return bd;
		}
		AbstractBeanDefinition specialServer = findServerForSpecialEnvironment();
		if (specialServer != null) {
			return specialServer;
		}
		RootBeanDefinition bd = new RootBeanDefinition(MBeanServerFactoryBean.class);
		bd.getPropertyValues().add("locateExistingServerIfPossible", Boolean.TRUE);

		// Mark as infrastructure bean and attach source location.
		bd.setRole(BeanDefinition.ROLE_INFRASTRUCTURE);
		bd.setSource(parserContext.extractSource(element));
		return bd;
	}
