	private Identifier determineTable(
			MappingDocument mappingDocument,
			String attributeName,
			List<RelationalValueSource> relationalValueSources) {
		String tableName = null;
		for ( RelationalValueSource relationalValueSource : relationalValueSources ) {
			// We need to get the containing table name for both columns and formulas,
			// particularly when a column/formula is for a property on a secondary table.
			if ( EqualsHelper.equals( tableName, relationalValueSource.getContainingTableName() ) ) {
				continue;
			}

			if ( tableName != null ) {
				throw new MappingException(
						String.format(
								Locale.ENGLISH,
								"Attribute [%s] referenced columns from multiple tables: %s, %s",
								attributeName,
								tableName,
								relationalValueSource.getContainingTableName()
						),
						mappingDocument.getOrigin()
				);
			}

			tableName = relationalValueSource.getContainingTableName();
		}

		return database.toIdentifier( tableName );
	}
