	@Test
	public void testEquals() {
		IOther a = new AllInstancesAreEqual();
		IOther b = new AllInstancesAreEqual();
		NopInterceptor i1 = new NopInterceptor();
		NopInterceptor i2 = new NopInterceptor();
		ProxyFactory pfa = new ProxyFactory(a);
		pfa.addAdvice(i1);
		ProxyFactory pfb = new ProxyFactory(b);
		pfb.addAdvice(i2);
		IOther proxyA = (IOther) createProxy(pfa);
		IOther proxyB = (IOther) createProxy(pfb);

		assertEquals(pfa.getAdvisors().length, pfb.getAdvisors().length);
		assertEquals(a, b);
		assertEquals(i1, i2);
		assertEquals(proxyA, proxyB);
		assertEquals(proxyA.hashCode(), proxyB.hashCode());
		assertFalse(proxyA.equals(a));

		// Equality checks were handled by the proxy
		assertEquals(0, i1.getCount());

		// When we invoke A, it's NopInterceptor will have count == 1
		// and won't think it's equal to B's NopInterceptor
		proxyA.absquatulate();
		assertEquals(1, i1.getCount());
		assertFalse(proxyA.equals(proxyB));
	}
