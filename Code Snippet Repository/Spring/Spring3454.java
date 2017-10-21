	@Test
	public void systemPropertiesSecurityManager() {
		GenericApplicationContext ac = new GenericApplicationContext();
		AnnotationConfigUtils.registerAnnotationConfigProcessors(ac);

		GenericBeanDefinition bd = new GenericBeanDefinition();
		bd.setBeanClass(TestBean.class);
		bd.getPropertyValues().add("country", "#{systemProperties.country}");
		ac.registerBeanDefinition("tb", bd);

		SecurityManager oldSecurityManager = System.getSecurityManager();
		try {
			System.setProperty("country", "NL");

			SecurityManager securityManager = new SecurityManager() {
				@Override
				public void checkPropertiesAccess() {
					throw new AccessControlException("Not Allowed");
				}
				@Override
				public void checkPermission(Permission perm) {
					// allow everything else
				}
			};
			System.setSecurityManager(securityManager);
			ac.refresh();

			TestBean tb = ac.getBean("tb", TestBean.class);
			assertEquals("NL", tb.getCountry());

		}
		finally {
			System.setSecurityManager(oldSecurityManager);
			System.getProperties().remove("country");
		}
	}
