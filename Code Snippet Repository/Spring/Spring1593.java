	@Test
	public void testInitSecurityAwarePrototypeBean() {
		final DefaultListableBeanFactory lbf = new DefaultListableBeanFactory();
		BeanDefinitionBuilder bdb = BeanDefinitionBuilder
				.genericBeanDefinition(NonPrivilegedBean.class).setScope(
						ConfigurableBeanFactory.SCOPE_PROTOTYPE)
				.setInitMethodName("init").setDestroyMethodName("destroy")
				.addConstructorArgValue("user1");
		lbf.registerBeanDefinition("test", bdb.getBeanDefinition());
		final Subject subject = new Subject();
		subject.getPrincipals().add(new TestPrincipal("user1"));

		NonPrivilegedBean bean = Subject.doAsPrivileged(
				subject, new PrivilegedAction<NonPrivilegedBean>() {
					@Override
					public NonPrivilegedBean run() {
						return lbf.getBean("test", NonPrivilegedBean.class);
					}
				}, null);
		assertNotNull(bean);
	}
