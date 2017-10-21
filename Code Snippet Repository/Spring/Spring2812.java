	@Test
	public void testProxyIsBoundBeforeTargetSourceInvoked() {
		final TestBean target = new TestBean();
		ProxyFactory pf = new ProxyFactory(target);
		pf.addAdvice(new DebugInterceptor());
		pf.setExposeProxy(true);
		final ITestBean proxy = (ITestBean) createProxy(pf);
		Advised config = (Advised) proxy;

		// This class just checks proxy is bound before getTarget() call
		config.setTargetSource(new TargetSource() {
			@Override
			public Class<?> getTargetClass() {
				return TestBean.class;
			}
			@Override
			public boolean isStatic() {
				return false;
			}
			@Override
			public Object getTarget() throws Exception {
				assertEquals(proxy, AopContext.currentProxy());
				return target;
			}
			@Override
			public void releaseTarget(Object target) throws Exception {
			}
		});

		// Just test anything: it will fail if context wasn't found
		assertEquals(0, proxy.getAge());
	}
