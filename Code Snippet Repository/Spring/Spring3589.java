	private SimpleRemoteSlsbInvokerInterceptor configuredInterceptor(
			final Context mockCtx, String jndiName) throws Exception {

		SimpleRemoteSlsbInvokerInterceptor si = createInterceptor();
		si.setJndiTemplate(new JndiTemplate() {
			@Override
			protected Context createInitialContext() {
				return mockCtx;
			}
		});
		si.setResourceRef(true);
		si.setJndiName(jndiName);

		return si;
	}
