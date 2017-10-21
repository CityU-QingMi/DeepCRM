	private void validateAliases(List<String> aliases) {
		// lets make sure we did not end up with duplicate aliases.  this can occur when the user supplied query
		// did not rename same-named columns.  e.g.:
		//		select u.username, u2.username from t_user u, t_user u2 ...
		//
		// the above will lead to an unworkable situation in most cases (the difference is how the driver/db
		// handle this situation.  But if the 'aliases' variable contains duplicate names, then we have that
		// troublesome condition, so lets throw an error.  See HHH-5992
		final HashSet<String> aliasesSet = new HashSet<String>();
		for ( String alias : aliases ) {
			validateAlias( alias );
			boolean alreadyExisted = !aliasesSet.add( alias );
			if ( alreadyExisted ) {
				throw new NonUniqueDiscoveredSqlAliasException(
						"Encountered a duplicated sql alias [" + alias + "] during auto-discovery of a native-sql query"
				);
			}
		}
	}
