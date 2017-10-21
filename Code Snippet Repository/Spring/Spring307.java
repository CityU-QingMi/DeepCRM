	@Test
	public void testPerThisAspect() throws SecurityException, NoSuchMethodException {
		TestBean target = new TestBean();
		int realAge = 65;
		target.setAge(realAge);
		TestBean itb = (TestBean) createProxy(target,
				getFixture().getAdvisors(new SingletonMetadataAwareAspectInstanceFactory(new PerThisAspect(), "someBean")),
				TestBean.class);
		assertEquals("Around advice must NOT apply", realAge, itb.getAge());

		Advised advised = (Advised) itb;
		// Will be ExposeInvocationInterceptor, synthetic instantiation advisor, 2 method advisors
		assertEquals(4, advised.getAdvisors().length);
		SyntheticInstantiationAdvisor sia = (SyntheticInstantiationAdvisor) advised.getAdvisors()[1];
		assertTrue(sia.getPointcut().getMethodMatcher().matches(TestBean.class.getMethod("getSpouse"), null));
		InstantiationModelAwarePointcutAdvisorImpl imapa = (InstantiationModelAwarePointcutAdvisorImpl) advised.getAdvisors()[2];
		LazySingletonAspectInstanceFactoryDecorator maaif =
				(LazySingletonAspectInstanceFactoryDecorator) imapa.getAspectInstanceFactory();
		assertFalse(maaif.isMaterialized());

		// Check that the perclause pointcut is valid
		assertTrue(maaif.getAspectMetadata().getPerClausePointcut().getMethodMatcher().matches(TestBean.class.getMethod("getSpouse"), null));
		assertNotSame(imapa.getDeclaredPointcut(), imapa.getPointcut());

		// Hit the method in the per clause to instantiate the aspect
		itb.getSpouse();

		assertTrue(maaif.isMaterialized());

		assertTrue(imapa.getDeclaredPointcut().getMethodMatcher().matches(TestBean.class.getMethod("getAge"), null));

		assertEquals("Around advice must apply", 0, itb.getAge());
		assertEquals("Around advice must apply", 1, itb.getAge());
	}
