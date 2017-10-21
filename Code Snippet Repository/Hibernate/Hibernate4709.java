		@Override
		public String injectAliases(OrderByAliasResolver aliasResolver) {
			String sql = sqlTemplate;
			for ( String columnReference : columnReferences ) {
				final String replacementToken = "{" + columnReference + "}";
				sql = sql.replace(
						replacementToken,
						aliasResolver.resolveTableAlias( columnReference ) + '.' + columnReference
				);
			}
			return sql;
		}
