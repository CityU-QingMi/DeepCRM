	public void validate() throws HibernateException {
		// for each of the defined parameters, make sure its value
		// has been set

		for ( final String parameterName : definition.getParameterNames() ) {
			if ( parameters.get( parameterName ) == null ) {
				throw new HibernateException(
						"Filter [" + getName() + "] parameter [" + parameterName + "] value not set"
				);
			}
		}
	}
