	@Test
	public void testLookupReturnsNull() throws Exception {
		String name = "foo";
		final Context context = mock(Context.class);
		given(context.lookup(name)).willReturn(null);

		JndiTemplate jt = new JndiTemplate() {
			@Override
			protected Context createInitialContext() {
				return context;
			}
		};

		try {
			jt.lookup(name);
			fail("Should have thrown NamingException");
		}
		catch (NameNotFoundException ex) {
			// Ok
		}
		verify(context).close();
	}
