	protected Object createProxy(Object target, List<Advisor> advisors, Class<?>... interfaces) {
		ProxyFactory pf = new ProxyFactory(target);
		if (interfaces.length > 1 || interfaces[0].isInterface()) {
			pf.setInterfaces(interfaces);
		}
		else {
			pf.setProxyTargetClass(true);
		}

		// Required everywhere we use AspectJ proxies
		pf.addAdvice(ExposeInvocationInterceptor.INSTANCE);

		for (Object a : advisors) {
			pf.addAdvisor((Advisor) a);
		}

		pf.setExposeProxy(true);
		return pf.getProxy();
	}
