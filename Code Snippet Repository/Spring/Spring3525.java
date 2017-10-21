	@Test
	public void securityManagerDisallowsAccessToSystemEnvironmentAndDisallowsAccessToIndividualKey() {
		SecurityManager securityManager = new SecurityManager() {
			@Override
			public void checkPermission(Permission perm) {
				// Disallowing access to System#getenv means that our
				// ReadOnlySystemAttributesMap will come into play.
				if ("getenv.*".equals(perm.getName())) {
					throw new AccessControlException("Accessing the system environment is disallowed");
				}
				// Disallowing access to the spring.profiles.active property means that
				// the BeanDefinitionReader won't be able to determine which profiles are
				// active. We should see an INFO-level message in the console about this
				// and as a result, any components marked with a non-default profile will
				// be ignored.
				if (("getenv." + AbstractEnvironment.ACTIVE_PROFILES_PROPERTY_NAME).equals(perm.getName())) {
					throw new AccessControlException(
							format("Accessing system environment variable [%s] is disallowed",
									AbstractEnvironment.ACTIVE_PROFILES_PROPERTY_NAME));
				}
			}
		};
		System.setSecurityManager(securityManager);

		DefaultListableBeanFactory bf = new DefaultListableBeanFactory();
		AnnotatedBeanDefinitionReader reader = new AnnotatedBeanDefinitionReader(bf);
		reader.register(C1.class);
		assertThat(bf.containsBean("c1"), is(false));
	}
