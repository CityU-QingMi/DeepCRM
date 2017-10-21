	@Test
	public void testTrustedExecution() throws Exception {
		beanFactory.setSecurityContextProvider(null);

		Permissions perms = new Permissions();
		perms.add(new AuthPermission("getSubject"));
		ProtectionDomain pd = new ProtectionDomain(null, perms);

		new AccessControlContext(new ProtectionDomain[] { pd });

		final Subject subject = new Subject();
		subject.getPrincipals().add(new TestPrincipal("user1"));

		// request the beans from non-privileged code
		Subject.doAsPrivileged(subject, new PrivilegedAction<Object>() {

			@Override
			public Object run() {
				// sanity check
				assertEquals("user1", getCurrentSubjectName());
				assertEquals(false, NonPrivilegedBean.destroyed);

				beanFactory.getBean("trusted-spring-callbacks");
				beanFactory.getBean("trusted-custom-init-destroy");
				// the factory is a prototype - ask for multiple instances
				beanFactory.getBean("trusted-spring-factory");
				beanFactory.getBean("trusted-spring-factory");
				beanFactory.getBean("trusted-spring-factory");

				beanFactory.getBean("trusted-factory-bean");
				beanFactory.getBean("trusted-static-factory-method");
				beanFactory.getBean("trusted-factory-method");
				beanFactory.getBean("trusted-property-injection");
				beanFactory.getBean("trusted-working-property-injection");

				beanFactory.destroySingletons();
				assertEquals(true, NonPrivilegedBean.destroyed);
				return null;
			}
		}, provider.getAccessControlContext());
	}
