	@Test
	public void testCommonInterceptorAndAdvisor() throws Exception {
		BeanFactory bf = new ClassPathXmlApplicationContext(COMMON_INTERCEPTORS_CONTEXT, CLASS);
		ITestBean test1 = (ITestBean) bf.getBean("test1");
		assertTrue(AopUtils.isAopProxy(test1));

		Lockable lockable1 = (Lockable) test1;
		NopInterceptor nop1 = (NopInterceptor) bf.getBean("nopInterceptor");
		NopInterceptor nop2 = (NopInterceptor) bf.getBean("pointcutAdvisor", Advisor.class).getAdvice();

		ITestBean test2 = (ITestBean) bf.getBean("test2");
		Lockable lockable2 = (Lockable) test2;

		// Locking should be independent; nop is shared
		assertFalse(lockable1.locked());
		assertFalse(lockable2.locked());
		// equals 2 calls on shared nop, because it's first and sees calls
		// against the Lockable interface introduced by the specific advisor
		assertEquals(2, nop1.getCount());
		assertEquals(0, nop2.getCount());
		lockable1.lock();
		assertTrue(lockable1.locked());
		assertFalse(lockable2.locked());
		assertEquals(5, nop1.getCount());
		assertEquals(0, nop2.getCount());

		PackageVisibleMethod packageVisibleMethod = (PackageVisibleMethod) bf.getBean("packageVisibleMethod");
		assertEquals(5, nop1.getCount());
		assertEquals(0, nop2.getCount());
		packageVisibleMethod.doSomething();
		assertEquals(6, nop1.getCount());
		assertEquals(1, nop2.getCount());
		assertTrue(packageVisibleMethod instanceof Lockable);
		Lockable lockable3 = (Lockable) packageVisibleMethod;
		lockable3.lock();
		assertTrue(lockable3.locked());
		lockable3.unlock();
		assertFalse(lockable3.locked());
	}
