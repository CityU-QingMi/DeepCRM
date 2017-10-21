	protected BeanManager findBeanManager() {
		BeanManager bm = null;
		try {
			Context initialContext = new InitialContext();
			if (jndiKey != null && jndiKey.trim().length() > 0) {
				// Check explicit configuration first, if given
				bm = lookup(initialContext, jndiKey);
			}
			if (bm == null) {
				// Check CDI default
				bm = lookup(initialContext, CDI_JNDIKEY_BEANMANAGER_COMP);
			}
			if (bm == null) {
				// Check WELD default
				bm = lookup(initialContext, CDI_JNDIKEY_BEANMANAGER_APP);
			}
			if (bm == null) {
				// Check Tomcat / Jetty default
				bm = lookup(initialContext, CDI_JNDIKEY_BEANMANAGER_COMP_ENV);
			}
			if (bm == null) {
				LOG.error("[findBeanManager]: Could not find BeanManager instance for any given JNDI key, giving up");
			}
		} catch ( NamingException e ) {
			LOG.error("[findBeanManager]: Unable to get InitialContext for BeanManager lookup", e);
		}
		return bm;
	}
