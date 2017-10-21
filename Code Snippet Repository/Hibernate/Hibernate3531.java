	public Object loadSequentialRowsForward(
			final ResultSet resultSet,
			final SharedSessionContractImplementor session,
			final QueryParameters queryParameters,
			final boolean returnProxies) throws HibernateException {

		// note that for sequential scrolling, we make the assumption that
		// the first persister element is the "root entity"

		try {
			if ( resultSet.isAfterLast() ) {
				// don't even bother trying to read further
				return null;
			}

			if ( resultSet.isBeforeFirst() ) {
				resultSet.next();
			}

			// We call getKeyFromResultSet() here so that we can know the
			// key value upon which to perform the breaking logic.  However,
			// it is also then called from getRowFromResultSet() which is certainly
			// not the most efficient.  But the call here is needed, and there
			// currently is no other way without refactoring of the doQuery()/getRowFromResultSet()
			// methods
			final EntityKey currentKey = getKeyFromResultSet(
					0,
					getEntityPersisters()[0],
					null,
					resultSet,
					session
			);

			return sequentialLoad( resultSet, session, queryParameters, returnProxies, currentKey );
		}
		catch (SQLException sqle) {
			throw factory.getJdbcServices().getSqlExceptionHelper().convert(
					sqle,
					"could not perform sequential read of results (forward)",
					getSQLString()
			);
		}
	}
