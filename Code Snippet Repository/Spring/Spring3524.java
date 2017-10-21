	@Test
	public void securityManagerDisallowsAccessToSystemEnvironmentButAllowsAccessToIndividualKeys() {
		SecurityManager securityManager = new SecurityManager() {
			@Override
			public void checkPermission(Permission perm) {
				// Disallowing access to System#getenv means that our
				// ReadOnlySystemAttributesMap will come into play.
				if ("getenv.*".equals(perm.getName())) {
					throw new AccessControlException("Accessing the system environment is disallowed");
				}
			}
		};
		System.setSecurityManager(securityManager);

		DefaultListableBeanFactory bf = new DefaultListableBeanFactory();
		AnnotatedBeanDefinitionReader reader = new AnnotatedBeanDefinitionReader(bf);
		reader.register(C1.class);
		assertThat(bf.containsBean("c1"), is(true));
	}
