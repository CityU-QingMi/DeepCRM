	@Test
	public void testCloneInvocationToProceedThreeTimes() throws Throwable {
		TestBean tb = new TestBean();
		ProxyFactory pc = new ProxyFactory(tb);
		pc.addInterface(ITestBean.class);

		MethodInterceptor twoBirthdayInterceptor = new MethodInterceptor() {
			@Override
			public Object invoke(MethodInvocation mi) throws Throwable {
				// Clone the invocation to proceed three times
				// "The Moor's Last Sigh": this technology can cause premature aging
				MethodInvocation clone1 = ((ReflectiveMethodInvocation) mi).invocableClone();
				MethodInvocation clone2 = ((ReflectiveMethodInvocation) mi).invocableClone();
				clone1.proceed();
				clone2.proceed();
				return mi.proceed();
			}
		};
		@SuppressWarnings("serial")
		StaticMethodMatcherPointcutAdvisor advisor = new StaticMethodMatcherPointcutAdvisor(twoBirthdayInterceptor) {
			@Override
			public boolean matches(Method m, @Nullable Class<?> targetClass) {
				return "haveBirthday".equals(m.getName());
			}
		};
		pc.addAdvisor(advisor);
		ITestBean it = (ITestBean) createProxy(pc);

		final int age = 20;
		it.setAge(age);
		assertEquals(age, it.getAge());
		// Should return the age before the third, AOP-induced birthday
		assertEquals(age + 2, it.haveBirthday());
		// Return the final age produced by 3 birthdays
		assertEquals(age + 3, it.getAge());
	}
