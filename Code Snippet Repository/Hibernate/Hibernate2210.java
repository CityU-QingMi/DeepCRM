	private Name parseName(String jndiName, Context context) {
		try {
			return context.getNameParser( "" ).parse( jndiName );
		}
		catch ( InvalidNameException e ) {
			throw new JndiNameException( "JNDI name [" + jndiName + "] was not valid", e );
		}
		catch ( NamingException e ) {
			throw new JndiException( "Error parsing JNDI name [" + jndiName + "]", e );
		}
	}
