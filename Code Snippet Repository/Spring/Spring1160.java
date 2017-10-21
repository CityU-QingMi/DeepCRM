	@SuppressWarnings("")
	@Test
	public void testInitSecurityAwarePrototypeBean() {
		final DefaultListableBeanFactory lbf = new DefaultListableBeanFactory();
		RootBeanDefinition bd = new RootBeanDefinition(TestSecuredBean.class);
		bd.setScope(ConfigurableBeanFactory.SCOPE_PROTOTYPE);
		bd.setInitMethodName("init");
		lbf.registerBeanDefinition("test", bd);
		final Subject subject = new Subject();
		subject.getPrincipals().add(new TestPrincipal("user1"));

		TestSecuredBean bean = (TestSecuredBean) Subject.doAsPrivileged(subject,
				new PrivilegedAction() {
					@Override
					public Object run() {
						return lbf.getBean("test");
					}
				}, null);
		assertNotNull(bean);
		assertEquals("user1", bean.getUserName());
	}
