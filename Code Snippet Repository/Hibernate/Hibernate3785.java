	private void registerSqlTableAliasMapping(String querySpaceUid, String sqlTableAlias) {
		if ( querySpaceUidToSqlTableAliasMap == null ) {
			querySpaceUidToSqlTableAliasMap = new HashMap<String, String>();
		}
		String old = querySpaceUidToSqlTableAliasMap.put( querySpaceUid, sqlTableAlias );
		if ( old != null ) {
			if ( old.equals( sqlTableAlias ) ) {
				// silently ignore...
			}
			else {
				throw new IllegalStateException(
						String.format(
								"Attempt to register multiple SQL table aliases [%s, %s, etc] against query space uid [%s]",
								old,
								sqlTableAlias,
								querySpaceUid
						)
				);
			}
		}
	}
