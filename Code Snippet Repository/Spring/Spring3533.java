	@Test
	public void testPropertyPlaceholderConfigurerWithSystemPropertyInLocation() {
		StaticApplicationContext ac = new StaticApplicationContext();
		MutablePropertyValues pvs = new MutablePropertyValues();
		pvs.add("spouse", new RuntimeBeanReference("${ref}"));
		ac.registerSingleton("tb", TestBean.class, pvs);
		pvs = new MutablePropertyValues();
		pvs.add("location", "${user.dir}/test");
		ac.registerSingleton("configurer", PropertyPlaceholderConfigurer.class, pvs);
		try {
			ac.refresh();
			fail("Should have thrown BeanInitializationException");
		}
		catch (BeanInitializationException ex) {
			// expected
			assertTrue(ex.getCause() instanceof FileNotFoundException);
			// slight hack for Linux/Unix systems
			String userDir = StringUtils.cleanPath(System.getProperty("user.dir"));
			if (userDir.startsWith("/")) {
				userDir = userDir.substring(1);
			}
			assertTrue(ex.getMessage().indexOf(userDir) != -1);
		}
	}
