	private ITestBean getIntroductionAdvisorProxy(TestBean target) {
		ProxyFactory pf = new ProxyFactory(ITestBean.class);
		pf.setProxyTargetClass(true);

		pf.addAdvisor(new LockMixinAdvisor());
		pf.setTarget(target);
		pf.setFrozen(true);
		pf.setExposeProxy(false);

		return (ITestBean) pf.getProxy();
	}
