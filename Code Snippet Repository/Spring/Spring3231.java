	@Test
	public void withScopedProxy() throws IOException, ClassNotFoundException {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
		ctx.register(ComponentScanWithScopedProxy.class);
		ctx.getBeanFactory().registerScope("myScope", new SimpleMapScope());
		ctx.refresh();
		// should cast to the interface
		FooService bean = (FooService) ctx.getBean("scopedProxyTestBean");
		// should be dynamic proxy
		assertThat(AopUtils.isJdkDynamicProxy(bean), is(true));
		// test serializability
		assertThat(bean.foo(1), equalTo("bar"));
		FooService deserialized = (FooService) SerializationTestUtils.serializeAndDeserialize(bean);
		assertThat(deserialized, notNullValue());
		assertThat(deserialized.foo(1), equalTo("bar"));
	}
