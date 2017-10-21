	@Test
	public void testValidSwaps() {
		SideEffectBean target1 = (SideEffectBean) beanFactory.getBean("target1");
		SideEffectBean target2 = (SideEffectBean) beanFactory.getBean("target2");

		SideEffectBean proxied = (SideEffectBean) beanFactory.getBean("swappable");
		assertEquals(target1.getCount(), proxied.getCount() );
		proxied.doWork();
		assertEquals(INITIAL_COUNT + 1, proxied.getCount() );

		HotSwappableTargetSource swapper = (HotSwappableTargetSource) beanFactory.getBean("swapper");
		Object old = swapper.swap(target2);
		assertEquals("Correct old target was returned", target1, old);

		// TODO should be able to make this assertion: need to fix target handling
		// in AdvisedSupport
		//assertEquals(target2, ((Advised) proxied).getTarget());

		assertEquals(20, proxied.getCount());
		proxied.doWork();
		assertEquals(21, target2.getCount());

		// Swap it back
		swapper.swap(target1);
		assertEquals(target1.getCount(), proxied.getCount());
	}
