	protected LocalSlsbInvokerInterceptor configuredInterceptor(final Context mockCtx, final String jndiName)
			throws Exception {

		LocalSlsbInvokerInterceptor si = new LocalSlsbInvokerInterceptor();
		si.setJndiTemplate(new JndiTemplate() {
			@Override
			protected Context createInitialContext() throws NamingException {
				return mockCtx;
			}
		});
		si.setJndiName(jndiName);
		si.setResourceRef(true);
		si.afterPropertiesSet();

		return si;
	}
