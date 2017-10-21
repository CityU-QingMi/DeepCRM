	@Test
	public void testLockingWorks() {
		Assume.group(TestGroup.LONG_RUNNING);

		Object introductionObject = ctx.getBean("introduction");
		assertFalse("Introduction should not be proxied", AopUtils.isAopProxy(introductionObject));

		Lockable lockable = (Lockable) testBeanProxy;
		assertFalse(lockable.locked());

		// Invoke a non-advised method
		testBeanProxy.getAge();

		testBeanProxy.setName("");
		lockable.lock();
		try {
			testBeanProxy.setName(" ");
			fail("Should be locked");
		}
		catch (IllegalStateException ex) {
			// expected
		}
	}
