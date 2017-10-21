	@Test
	public void testTargetCanGetInvocationEvenIfNoAdviceChain() throws Throwable {
		NeedsToSeeProxy target = new NeedsToSeeProxy();
		AdvisedSupport pc = new AdvisedSupport(INeedsToSeeProxy.class);
		pc.setTarget(target);
		pc.setExposeProxy(true);

		// Now let's try it with the special target
		AopProxy aop = createAopProxy(pc);
		INeedsToSeeProxy proxied = (INeedsToSeeProxy) aop.getProxy();
		// It will complain if it can't get the proxy
		proxied.incrementViaProxy();
	}
