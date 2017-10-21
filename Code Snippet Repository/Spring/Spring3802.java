	@Test
	public void testBind() throws Exception {
		Object o = new Object();
		String name = "foo";
		final Context context = mock(Context.class);

		JndiTemplate jt = new JndiTemplate() {
			@Override
			protected Context createInitialContext() {
				return context;
			}
		};

		jt.bind(name, o);
		verify(context).bind(name, o);
		verify(context).close();
	}
