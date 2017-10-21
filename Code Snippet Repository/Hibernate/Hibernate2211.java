	@Override
	public void bind(String jndiName, Object value) {
		final InitialContext initialContext = buildInitialContext();
		final Name name = parseName( jndiName, initialContext );
		try {
			bind( name, value, initialContext );
		}
		finally {
			cleanUp( initialContext );
		}
	}
