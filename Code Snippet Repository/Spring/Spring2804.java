	@Test
	public void testExistingProxyChangesTarget() throws Throwable {
		TestBean tb1 = new TestBean();
		tb1.setAge(33);

		TestBean tb2 = new TestBean();
		tb2.setAge(26);
		tb2.setName("Juergen");
		TestBean tb3 = new TestBean();
		tb3.setAge(37);
		ProxyFactory pc = new ProxyFactory(tb1);
		NopInterceptor nop = new NopInterceptor();
		pc.addAdvice(nop);
		ITestBean proxy = (ITestBean) createProxy(pc);
		assertEquals(nop.getCount(), 0);
		assertEquals(tb1.getAge(), proxy.getAge());
		assertEquals(nop.getCount(), 1);
		// Change to a new static target
		pc.setTarget(tb2);
		assertEquals(tb2.getAge(), proxy.getAge());
		assertEquals(nop.getCount(), 2);

		// Change to a new dynamic target
		HotSwappableTargetSource hts = new HotSwappableTargetSource(tb3);
		pc.setTargetSource(hts);
		assertEquals(tb3.getAge(), proxy.getAge());
		assertEquals(nop.getCount(), 3);
		hts.swap(tb1);
		assertEquals(tb1.getAge(), proxy.getAge());
		tb1.setName("Colin");
		assertEquals(tb1.getName(), proxy.getName());
		assertEquals(nop.getCount(), 5);

		// Change back, relying on casting to Advised
		Advised advised = (Advised) proxy;
		assertSame(hts, advised.getTargetSource());
		SingletonTargetSource sts = new SingletonTargetSource(tb2);
		advised.setTargetSource(sts);
		assertEquals(tb2.getName(), proxy.getName());
		assertSame(sts, advised.getTargetSource());
		assertEquals(tb2.getAge(), proxy.getAge());
	}
