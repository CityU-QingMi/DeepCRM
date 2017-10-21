	@Override
	public Object locate(String jndiName) {
		final InitialContext initialContext = buildInitialContext();
		final Name name = parseName( jndiName, initialContext );
		try {
			return initialContext.lookup( name );
		}
		catch ( NamingException e ) {
			throw new JndiException( "Unable to lookup JNDI name [" + jndiName + "]", e );
		}
		finally {
			cleanUp( initialContext );
		}
	}
