		@Override
		public String[] toColumns(String alias, String propertyName) throws QueryException {
			validate( propertyName );
			final String joinTableAlias = joinSequence.getFirstJoin().getAlias();
			if ( CollectionPropertyNames.COLLECTION_INDEX.equals( propertyName ) ) {
				return queryableCollection.toColumns( joinTableAlias, propertyName );
			}

			final String[] cols = queryableCollection.getIndexColumnNames( joinTableAlias );
			if ( CollectionPropertyNames.COLLECTION_MIN_INDEX.equals( propertyName ) ) {
				if ( cols.length != 1 ) {
					throw new QueryException( "composite collection index in minIndex()" );
				}
				return new String[] {"min(" + cols[0] + ')'};
			}
			else {
				if ( cols.length != 1 ) {
					throw new QueryException( "composite collection index in maxIndex()" );
				}
				return new String[] {"max(" + cols[0] + ')'};
			}
		}
