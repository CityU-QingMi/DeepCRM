	private void testTestBeanIntroduction(ProxyFactory pc) {
		int newAge = 65;
		ITestBean itb = (ITestBean) createProxy(pc);
		itb.setAge(newAge);
		assertEquals(newAge, itb.getAge());

		Lockable lockable = (Lockable) itb;
		assertFalse(lockable.locked());
		lockable.lock();

		assertEquals(newAge, itb.getAge());
		try {
			itb.setAge(1);
			fail("Setters should fail when locked");
		}
		catch (LockedException ex) {
			// ok
		}
		assertEquals(newAge, itb.getAge());

		// Unlock
		assertTrue(lockable.locked());
		lockable.unlock();
		itb.setAge(1);
		assertEquals(1, itb.getAge());
	}
