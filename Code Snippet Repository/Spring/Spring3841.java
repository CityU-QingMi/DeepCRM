		public DynamicAsyncInterfaceBean() {
			ProxyFactory pf = new ProxyFactory(new HashMap<>());
			DefaultIntroductionAdvisor advisor = new DefaultIntroductionAdvisor(new MethodInterceptor() {
				@Override
				public Object invoke(MethodInvocation invocation) throws Throwable {
					assertTrue(!Thread.currentThread().getName().equals(originalThreadName));
					if (Future.class.equals(invocation.getMethod().getReturnType())) {
						return new AsyncResult<>(invocation.getArguments()[0].toString());
					}
					return null;
				}
			});
			advisor.addInterface(AsyncInterface.class);
			pf.addAdvisor(advisor);
			this.proxy = (AsyncInterface) pf.getProxy();
		}
