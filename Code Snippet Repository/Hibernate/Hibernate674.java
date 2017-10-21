		@Override
		public Table resolveTable(Identifier tableName) {
			if ( tableName == null ) {
				return primaryTable;
			}

			if ( Identifier.areEqual( primaryTableLogicalName, tableName ) ) {
				return primaryTable;
			}

			Join secondaryTableJoin = null;
			if ( secondaryTableJoinMap != null ) {
				//secondaryTableJoin = secondaryTableJoinMap.get( tableName );
				secondaryTableJoin = secondaryTableJoinMap.get( tableName.getCanonicalName() );
			}

			if ( secondaryTableJoin != null ) {
				return secondaryTableJoin.getTable();
			}

			if ( superEntityTableXref != null ) {
				return superEntityTableXref.resolveTable( tableName );
			}

			return null;
		}
