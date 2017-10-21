	@Override
	public String resolveSqlTableAliasFromQuerySpaceUid(String querySpaceUid) {
		String alias = null;
		if ( querySpaceUidToSqlTableAliasMap != null ) {
			alias = querySpaceUidToSqlTableAliasMap.get( querySpaceUid );
		}

		if ( alias == null ) {
			if ( compositeQuerySpaceUidToSqlTableAliasMap != null ) {
				alias = compositeQuerySpaceUidToSqlTableAliasMap.get( querySpaceUid );
			}
		}

		return alias;
	}
