	@Override
	public void addListener(String jndiName, NamespaceChangeListener listener) {
		final InitialContext initialContext = buildInitialContext();
		final Name name = parseName( jndiName, initialContext );
		try {
			( (EventContext) initialContext ).addNamingListener( name, EventContext.OBJECT_SCOPE, listener );
		}
		catch (Exception e) {
			throw new JndiException( "Unable to bind listener to namespace [" + name + "]", e );
		}
		finally {
			cleanUp( initialContext );
		}
	}
