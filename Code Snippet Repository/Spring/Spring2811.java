	@SuppressWarnings("")
	@Test
	public void testOverloadedMethodsWithDifferentAdvice() throws Throwable {
		Overloads target = new Overloads();
		ProxyFactory pc = new ProxyFactory(target);

		NopInterceptor overLoadVoids = new NopInterceptor();
		pc.addAdvisor(new StaticMethodMatcherPointcutAdvisor(overLoadVoids) {
			@Override
			public boolean matches(Method m, @Nullable Class<?> targetClass) {
				return m.getName().equals("overload") && m.getParameterCount() == 0;
			}
		});

		NopInterceptor overLoadInts = new NopInterceptor();
		pc.addAdvisor(new StaticMethodMatcherPointcutAdvisor(overLoadInts) {
			@Override
			public boolean matches(Method m, @Nullable Class<?> targetClass) {
				return m.getName().equals("overload") && m.getParameterCount() == 1 &&
						m.getParameterTypes()[0].equals(int.class);
			}
		});

		IOverloads proxy = (IOverloads) createProxy(pc);
		assertEquals(0, overLoadInts.getCount());
		assertEquals(0, overLoadVoids.getCount());
		proxy.overload();
		assertEquals(0, overLoadInts.getCount());
		assertEquals(1, overLoadVoids.getCount());
		assertEquals(25, proxy.overload(25));
		assertEquals(1, overLoadInts.getCount());
		assertEquals(1, overLoadVoids.getCount());
		proxy.noAdvice();
		assertEquals(1, overLoadInts.getCount());
		assertEquals(1, overLoadVoids.getCount());
	}
