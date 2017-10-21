	@Test
	public void invokeListenerInvalidProxy() {
		Object target = new InvalidProxyTestBean();
		ProxyFactory proxyFactory = new ProxyFactory();
		proxyFactory.setTarget(target);
		proxyFactory.addInterface(SimpleService.class);
		Object bean = proxyFactory.getProxy(getClass().getClassLoader());

		Method method = ReflectionUtils.findMethod(
				InvalidProxyTestBean.class, "handleIt2", ApplicationEvent.class);
		StaticApplicationListenerMethodAdapter listener =
				new StaticApplicationListenerMethodAdapter(method, bean);
		this.thrown.expect(IllegalStateException.class);
		this.thrown.expectMessage("handleIt2");
		listener.onApplicationEvent(createGenericTestEvent("test"));
	}
