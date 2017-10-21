	protected Object createRefreshableProxy(TargetSource ts, @Nullable Class<?>[] interfaces, boolean proxyTargetClass) {
		ProxyFactory proxyFactory = new ProxyFactory();
		proxyFactory.setTargetSource(ts);
		ClassLoader classLoader = this.beanClassLoader;

		if (interfaces != null) {
			proxyFactory.setInterfaces(interfaces);
		}
		else {
			Class<?> targetClass = ts.getTargetClass();
			if (targetClass != null) {
				proxyFactory.setInterfaces(ClassUtils.getAllInterfacesForClass(targetClass, this.beanClassLoader));
			}
		}

		if (proxyTargetClass) {
			classLoader = null;  // force use of Class.getClassLoader()
			proxyFactory.setProxyTargetClass(true);
		}

		DelegatingIntroductionInterceptor introduction = new DelegatingIntroductionInterceptor(ts);
		introduction.suppressInterface(TargetSource.class);
		proxyFactory.addAdvice(introduction);

		return proxyFactory.getProxy(classLoader);
	}
