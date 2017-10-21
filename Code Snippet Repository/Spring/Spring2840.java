	@Test
	public void testPackageMethodInvocationWithDifferentClassLoader() {
		ClassLoader child = new ClassLoader(getClass().getClassLoader()) {
		};

		PackageMethodTestBean bean = new PackageMethodTestBean();
		bean.value = "foo";
		mockTargetSource.setTarget(bean);

		AdvisedSupport as = new AdvisedSupport();
		as.setTargetSource(mockTargetSource);
		as.addAdvice(new NopInterceptor());
		AopProxy aop = new CglibAopProxy(as);

		PackageMethodTestBean proxy = (PackageMethodTestBean) aop.getProxy(child);
		assertTrue(AopUtils.isCglibProxy(proxy));
		assertNotEquals(proxy.getClass().getClassLoader(), bean.getClass().getClassLoader());
		assertNull(proxy.getString());  // we're stuck in the proxy instance
	}
