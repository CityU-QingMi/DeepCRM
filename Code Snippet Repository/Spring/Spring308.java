	@Test
	public void testPerTypeWithinAspect() throws SecurityException, NoSuchMethodException {
		TestBean target = new TestBean();
		int realAge = 65;
		target.setAge(realAge);
		PerTypeWithinAspectInstanceFactory aif = new PerTypeWithinAspectInstanceFactory();
		TestBean itb = (TestBean) createProxy(target,
				getFixture().getAdvisors(aif),
				TestBean.class);
		assertEquals("No method calls", 0, aif.getInstantiationCount());
		assertEquals("Around advice must now apply", 0, itb.getAge());

		Advised advised = (Advised) itb;
		// Will be ExposeInvocationInterceptor, synthetic instantiation advisor, 2 method advisors
		assertEquals(4, advised.getAdvisors().length);
		SyntheticInstantiationAdvisor sia = (SyntheticInstantiationAdvisor) advised.getAdvisors()[1];
		assertTrue(sia.getPointcut().getMethodMatcher().matches(TestBean.class.getMethod("getSpouse"), null));
		InstantiationModelAwarePointcutAdvisorImpl imapa = (InstantiationModelAwarePointcutAdvisorImpl) advised.getAdvisors()[2];
		LazySingletonAspectInstanceFactoryDecorator maaif =
				(LazySingletonAspectInstanceFactoryDecorator) imapa.getAspectInstanceFactory();
		assertTrue(maaif.isMaterialized());

		// Check that the perclause pointcut is valid
		assertTrue(maaif.getAspectMetadata().getPerClausePointcut().getMethodMatcher().matches(TestBean.class.getMethod("getSpouse"), null));
		assertNotSame(imapa.getDeclaredPointcut(), imapa.getPointcut());

		// Hit the method in the per clause to instantiate the aspect
		itb.getSpouse();

		assertTrue(maaif.isMaterialized());

		assertTrue(imapa.getDeclaredPointcut().getMethodMatcher().matches(TestBean.class.getMethod("getAge"), null));

		assertEquals("Around advice must still apply", 1, itb.getAge());
		assertEquals("Around advice must still apply", 2, itb.getAge());

		TestBean itb2 = (TestBean) createProxy(target,
				getFixture().getAdvisors(aif),
				TestBean.class);
		assertEquals(1, aif.getInstantiationCount());
		assertEquals("Around advice be independent for second instance", 0, itb2.getAge());
		assertEquals(2, aif.getInstantiationCount());
	}
