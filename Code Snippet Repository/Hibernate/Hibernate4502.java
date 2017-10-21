	@Override
	public Parameter<?> getParameter(int position) {
		// It is important to understand that there are 2 completely distinct conceptualization of
		// "positional parameters" in play here:
		//		1) The legacy Hibernate concept is akin to JDBC PreparedStatement parameters.  Very limited and
		//			deprecated since 5.x.  These are numbered starting from 0 and kept in the
		//			ParameterMetadata positional-parameter array keyed by this zero-based position
		//		2) JPA's definition is really just a named parameter, but expected to explicitly be
		//			sequential integers starting from 1 (ONE); they can repeat.
		//
		// It is considered illegal to mix positional-parameter with named parameters of any kind.  So therefore.
		// if ParameterMetadata reports that it has any positional-parameters it is talking about the
		// legacy Hibernate concept.
		// lookup jpa-based positional parameters first by name.
		try {
			if ( getParameterMetadata().getPositionalParameterCount() == 0 ) {
				try {
					return getParameterMetadata().getQueryParameter( Integer.toString( position ) );
				}
				catch (HibernateException e) {
					throw new QueryParameterException( "could not locate parameter at position [" + position + "]" );
				}
			}
			// fallback to ordinal lookup
			return getParameterMetadata().getQueryParameter( position );
		}
		catch (HibernateException e) {
			throw getExceptionConverter().convert( e );
		}
	}
