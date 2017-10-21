	@Override
	public void unbind(String jndiName) {
		final InitialContext initialContext = buildInitialContext();
		final Name name = parseName( jndiName, initialContext );
		try {
			initialContext.unbind( name );
		}
		catch (Exception e) {
			throw new JndiException( "Error performing unbind [" + name + "]", e );
		}
		finally {
			cleanUp( initialContext );
		}
	}
