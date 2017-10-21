	@Override
	protected Set<ConfigurableApplicationContext> findApplicationContexts() {
		Set<ConfigurableApplicationContext> contexts = new LinkedHashSet<>();
		Enumeration<String> attrNames = this.servletContext.getAttributeNames();
		while (attrNames.hasMoreElements()) {
			String attrName = attrNames.nextElement();
			Object attrValue = this.servletContext.getAttribute(attrName);
			if (attrValue instanceof ConfigurableApplicationContext) {
				contexts.add((ConfigurableApplicationContext) attrValue);
			}
		}
		return contexts;
	}
