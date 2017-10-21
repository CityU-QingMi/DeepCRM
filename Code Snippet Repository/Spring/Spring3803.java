	@Test
	public void testRebind() throws Exception {
		Object o = new Object();
		String name = "foo";
		final Context context = mock(Context.class);

		JndiTemplate jt = new JndiTemplate() {
			@Override
			protected Context createInitialContext() {
				return context;
			}
		};

		jt.rebind(name, o);
		verify(context).rebind(name, o);
		verify(context).close();
;
	}
