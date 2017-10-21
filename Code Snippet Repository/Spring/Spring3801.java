	@Test
	public void testLookupFailsWithTypeMismatch() throws Exception {
		Object o = new Object();
		String name = "foo";
		final Context context = mock(Context.class);
		given(context.lookup(name)).willReturn(o);

		JndiTemplate jt = new JndiTemplate() {
			@Override
			protected Context createInitialContext() {
				return context;
			}
		};

		try {
			jt.lookup(name, String.class);
			fail("Should have thrown TypeMismatchNamingException");
		}
		catch (TypeMismatchNamingException ex) {
			// Ok
		}
		verify(context).close();
	}
