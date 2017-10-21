	private EmbeddedCacheManager locateCacheManager(String jndiNamespace, Properties jndiProperties) {
		Context ctx = null;
		try {
			ctx = new InitialContext( jndiProperties );
			return (EmbeddedCacheManager) ctx.lookup( jndiNamespace );
		}
		catch (NamingException ne) {
			throw log.unableToRetrieveCmFromJndi(jndiNamespace);
		}
		finally {
			if ( ctx != null ) {
				try {
					ctx.close();
				}
				catch (NamingException ne) {
					log.unableToReleaseContext(ne);
				}
			}
		}
	}
