	@Nullable
	static AbstractBeanDefinition findServerForSpecialEnvironment() {
		if (weblogicPresent) {
			RootBeanDefinition bd = new RootBeanDefinition(JndiObjectFactoryBean.class);
			bd.getPropertyValues().add("jndiName", "java:comp/env/jmx/runtime");
			return bd;
		}
		else if (webspherePresent) {
			return new RootBeanDefinition(WebSphereMBeanServerFactoryBean.class);
		}
		else {
			return null;
		}
	}
