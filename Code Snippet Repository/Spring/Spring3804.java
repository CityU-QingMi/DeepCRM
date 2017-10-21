	@Test
	public void testUnbind() throws Exception {
		String name = "something";
		final Context context = mock(Context.class);

		JndiTemplate jt = new JndiTemplate() {
			@Override
			protected Context createInitialContext() {
				return context;
			}
		};

		jt.unbind(name);
		verify(context).unbind(name);
		verify(context).close();
	}
