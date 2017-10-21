	private void doTestValueInjection(BeanFactory context) {
		System.clearProperty("myProp");

		TestBean testBean = context.getBean("testBean", TestBean.class);
		assertNull(testBean.getName());

		testBean = context.getBean("testBean2", TestBean.class);
		assertNull(testBean.getName());

		System.setProperty("myProp", "foo");

		testBean = context.getBean("testBean", TestBean.class);
		assertThat(testBean.getName(), equalTo("foo"));

		testBean = context.getBean("testBean2", TestBean.class);
		assertThat(testBean.getName(), equalTo("foo"));

		System.clearProperty("myProp");

		testBean = context.getBean("testBean", TestBean.class);
		assertNull(testBean.getName());

		testBean = context.getBean("testBean2", TestBean.class);
		assertNull(testBean.getName());
	}
