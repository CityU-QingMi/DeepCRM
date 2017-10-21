	@Test
	public void testLookupSucceeds() throws Exception {
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

		Object o2 = jt.lookup(name);
		assertEquals(o, o2);
		verify(context).close();
	}
