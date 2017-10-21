	private void testMultipleThreads(int concurrencyLimit) {
		TestBean tb = new TestBean();
		ProxyFactory proxyFactory = new ProxyFactory();
		proxyFactory.setInterfaces(new Class[] {ITestBean.class});
		ConcurrencyThrottleInterceptor cti = new ConcurrencyThrottleInterceptor();
		cti.setConcurrencyLimit(concurrencyLimit);
		proxyFactory.addAdvice(cti);
		proxyFactory.setTarget(tb);
		ITestBean proxy = (ITestBean) proxyFactory.getProxy();

		Thread[] threads = new Thread[NR_OF_THREADS];
		for (int i = 0; i < NR_OF_THREADS; i++) {
			threads[i] = new ConcurrencyThread(proxy, null);
			threads[i].start();
		}
		for (int i = 0; i < NR_OF_THREADS / 10; i++) {
			try {
				Thread.sleep(5);
			}
			catch (InterruptedException ex) {
				ex.printStackTrace();
			}
			threads[i] = new ConcurrencyThread(proxy,
					i % 2 == 0 ? (Throwable) new OutOfMemoryError() : (Throwable) new IllegalStateException());
			threads[i].start();
		}
		for (int i = 0; i < NR_OF_THREADS; i++) {
			try {
				threads[i].join();
			}
			catch (InterruptedException ex) {
				ex.printStackTrace();
			}
		}
	}
