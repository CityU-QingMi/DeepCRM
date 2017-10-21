	@Test
	public void testJdkIntroductionAppliesToCreatedObjectsNotFactoryBean() {
		ITestBean tb = (ITestBean) beanFactory.getBean("factory-introductionUsingJdk");
		NopInterceptor nop = (NopInterceptor) beanFactory.getBean("introductionNopInterceptor");
		assertEquals("NOP should not have done any work yet", 0, nop.getCount());
		assertTrue(AopUtils.isJdkDynamicProxy(tb));
		int age = 5;
		tb.setAge(age);
		assertEquals(age, tb.getAge());
		assertTrue("Introduction was made", tb instanceof TimeStamped);
		assertEquals(0, ((TimeStamped) tb).getTimeStamp());
		assertEquals(3, nop.getCount());

		ITestBean tb2 = (ITestBean) beanFactory.getBean("second-introductionUsingJdk");

		// Check two per-instance mixins were distinct
		Lockable lockable1 = (Lockable) tb;
		Lockable lockable2 = (Lockable) tb2;
		assertFalse(lockable1.locked());
		assertFalse(lockable2.locked());
		tb.setAge(65);
		assertEquals(65, tb.getAge());
		lockable1.lock();
		assertTrue(lockable1.locked());
		// Shouldn't affect second
		assertFalse(lockable2.locked());
		// Can still mod second object
		tb2.setAge(12);
		// But can't mod first
		try {
			tb.setAge(6);
			fail("Mixin should have locked this object");
		}
		catch (LockedException ex) {
			// Ok
		}
	}
