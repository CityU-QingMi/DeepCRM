	public Object lookup(final String name) throws NamingException {
		if (logger.isDebugEnabled()) {
			logger.debug("Looking up JNDI object with name [" + name + "]");
		}
		Object result = execute(ctx -> ctx.lookup(name));
		if (result == null) {
			throw new NameNotFoundException(
					"JNDI object with [" + name + "] not found: JNDI implementation returned null");
		}
		return result;
	}
