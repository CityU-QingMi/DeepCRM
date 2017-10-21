	@Test
	public void testSpr10744() throws Exception {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.getBeanFactory().registerScope("myTestScope", new MyTestScope());
		context.register(MyTestConfiguration.class);
		context.refresh();

		Foo bean1 = context.getBean("foo", Foo.class);
		Foo bean2 = context.getBean("foo", Foo.class);
		assertThat(bean1, sameInstance(bean2));

		// Should not have invoked constructor for the proxy instance
		assertThat(createCount, equalTo(0));
		assertThat(scopeCount, equalTo(0));

		// Proxy mode should create new scoped object on each method call
		bean1.getMessage();
		assertThat(createCount, equalTo(1));
		assertThat(scopeCount, equalTo(1));
		bean1.getMessage();
		assertThat(createCount, equalTo(2));
		assertThat(scopeCount, equalTo(2));

		context.close();
	}
