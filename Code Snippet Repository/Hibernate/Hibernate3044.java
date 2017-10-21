	public Object next() throws HibernateException {
		if ( !hasNext ) {
			throw new NoSuchElementException( "No more results" );
		}
		boolean sessionDefaultReadOnlyOrig = session.isDefaultReadOnly();
		session.setDefaultReadOnly( readOnly );
		try {
			boolean isHolder = holderInstantiator.isRequired();

			LOG.debugf( "Assembling results" );
			if ( single && !isHolder ) {
				currentResult = types[0].nullSafeGet( rs, names[0], session, null );
			}
			else {
				Object[] currentResults = new Object[types.length];
				for ( int i = 0; i < types.length; i++ ) {
					currentResults[i] = types[i].nullSafeGet( rs, names[i], session, null );
				}

				if ( isHolder ) {
					currentResult = holderInstantiator.instantiate( currentResults );
				}
				else {
					currentResult = currentResults;
				}
			}

			postNext();
			LOG.debugf( "Returning current results" );
			return currentResult;
		}
		catch (SQLException sqle) {
			throw session.getFactory().getSQLExceptionHelper().convert(
					sqle,
					"could not get next iterator result"
			);
		}
		finally {
			session.setDefaultReadOnly( sessionDefaultReadOnlyOrig );
		}
	}
