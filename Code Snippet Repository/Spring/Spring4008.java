	@Test
	public void testRawJdkProxy() throws Exception {
		ModelMap map = new ModelMap();
		Object proxy = Proxy.newProxyInstance(
				getClass().getClassLoader(),
				new Class<?>[] {Map.class},
				new InvocationHandler() {
					@Override
					public Object invoke(Object proxy, Method method, Object[] args) {
						return "proxy";
					}
				});
		map.addAttribute(proxy);
		assertSame(proxy, map.get("map"));
	}
