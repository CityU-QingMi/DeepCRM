		public Join locateJoin(Identifier tableName) {
			if ( tableName == null ) {
				return null;
			}

			Join join = null;
			if ( secondaryTableJoinMap != null ) {
				join = secondaryTableJoinMap.get( tableName.getCanonicalName() );
			}

			if ( join != null ) {
				return join;
			}

			if ( superEntityTableXref != null ) {
				return superEntityTableXref.locateJoin( tableName );
			}

			return null;
		}
