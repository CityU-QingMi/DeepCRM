	protected BeanManager lookup( Context context, String jndiKeyToCheck ) {
		LOG.info("[lookup]: Checking for BeanManager under JNDI key {}", jndiKeyToCheck);
		BeanManager result = null;
		try {
			result = (BeanManager) context.lookup(jndiKeyToCheck);
		} catch ( NamingException e ) {
			LOG.debug("[lookup]: BeanManager lookup failed for JNDI key {}", jndiKeyToCheck, e);
		}
		return result;
	}
