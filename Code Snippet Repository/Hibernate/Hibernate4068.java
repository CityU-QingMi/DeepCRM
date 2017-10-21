		@Override
		public String resolveTableAlias(String columnReference) {
			if ( elementPersister == null ) {
				// we have collection of non-entity elements...
				return rootAlias;
			}
			else {
				return ( (Loadable) elementPersister ).getTableAliasForColumn( columnReference, rootAlias );
			}
		}
