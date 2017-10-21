	@Test
	public void testReuseDestroyedTarget() {
		ThreadLocalTargetSource source = (ThreadLocalTargetSource)this.beanFactory.getBean("threadLocalTs");

		// try first time
		source.getTarget();
		source.destroy();

		// try second time
		try {
			source.getTarget();
		}
		catch (NullPointerException ex) {
			fail("Should not throw NPE");
		}
	}
