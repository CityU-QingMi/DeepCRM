	@Test
	public void accessAfterClosing() {
		GenericApplicationContext ac = new GenericApplicationContext();
		ac.registerBeanDefinition("testBean", new RootBeanDefinition(String.class));
		ac.refresh();

		assertSame(ac.getBean("testBean"), ac.getBean(String.class));
		assertSame(ac.getAutowireCapableBeanFactory().getBean("testBean"),
				ac.getAutowireCapableBeanFactory().getBean(String.class));

		ac.close();

		try {
			assertSame(ac.getBean("testBean"), ac.getBean(String.class));
			fail("Should have thrown IllegalStateException");
		}
		catch (IllegalStateException ex) {
			// expected
		}

		try {
			assertSame(ac.getAutowireCapableBeanFactory().getBean("testBean"),
					ac.getAutowireCapableBeanFactory().getBean(String.class));
			fail("Should have thrown IllegalStateException");
		}
		catch (IllegalStateException ex) {
			// expected
		}
	}
