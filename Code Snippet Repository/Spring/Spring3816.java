	@Test
	public void remoteInvocation() throws NoSuchMethodException {
		// let's see if the remote invocation object works

		final RemoteBean rb = new RemoteBean();
		final Method setNameMethod = rb.getClass().getDeclaredMethod("setName", String.class);

		MethodInvocation mi = new MethodInvocation() {
			@Override
			public Method getMethod() {
				return setNameMethod;
			}
			@Override
			public Object[] getArguments() {
				return new Object[] {"bla"};
			}
			@Override
			public Object proceed() throws Throwable {
				throw new UnsupportedOperationException();
			}
			@Override
			public Object getThis() {
				return rb;
			}
			@Override
			public AccessibleObject getStaticPart() {
				return setNameMethod;
			}
		};

		RemoteInvocation inv = new RemoteInvocation(mi);

		assertEquals("setName", inv.getMethodName());
		assertEquals("bla", inv.getArguments()[0]);
		assertEquals(String.class, inv.getParameterTypes()[0]);

		// this is a bit BS, but we need to test it
		inv = new RemoteInvocation();
		inv.setArguments(new Object[] { "bla" });
		assertEquals("bla", inv.getArguments()[0]);
		inv.setMethodName("setName");
		assertEquals("setName", inv.getMethodName());
		inv.setParameterTypes(new Class<?>[] {String.class});
		assertEquals(String.class, inv.getParameterTypes()[0]);

		inv = new RemoteInvocation("setName", new Class<?>[] {String.class}, new Object[] {"bla"});
		assertEquals("bla", inv.getArguments()[0]);
		assertEquals("setName", inv.getMethodName());
		assertEquals(String.class, inv.getParameterTypes()[0]);
	}
