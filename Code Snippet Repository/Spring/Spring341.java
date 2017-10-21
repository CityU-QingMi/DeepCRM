	@Test
	public void testValidInvocation() throws Throwable {
		Method m = Object.class.getMethod("hashCode");
		Object proxy = new Object();
		final Object returnValue = new Object();
		List<Object> is = new LinkedList<>();
		is.add(new MethodInterceptor() {
			@Override
			public Object invoke(MethodInvocation invocation) throws Throwable {
				return returnValue;
			}
		});
			ReflectiveMethodInvocation invocation = new ReflectiveMethodInvocation(proxy, null, //?
		m, null, null, is // list
	);
		Object rv = invocation.proceed();
		assertTrue("correct response", rv == returnValue);
	}
