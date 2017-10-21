	@Override
	protected void addColumnBinding(SimpleValue value) {
		if ( StringHelper.isEmpty( mappedBy ) ) {
			// was the column explicitly quoted in the mapping/annotation
			// TODO: in metamodel, we need to better split global quoting and explicit quoting w/ respect to logical names
			boolean isLogicalColumnQuoted = StringHelper.isQuoted( getLogicalColumnName() );
			
			final ObjectNameNormalizer nameNormalizer = getBuildingContext().getObjectNameNormalizer();
			final String logicalColumnName = nameNormalizer.normalizeIdentifierQuotingAsString( getLogicalColumnName() );
			final String referencedColumn = nameNormalizer.normalizeIdentifierQuotingAsString( getReferencedColumn() );
			final String unquotedLogColName = StringHelper.unquote( logicalColumnName );
			final String unquotedRefColumn = StringHelper.unquote( referencedColumn );

			String logicalCollectionColumnName = StringHelper.isNotEmpty( unquotedLogColName )
					? unquotedLogColName
					: getPropertyName() + '_' + unquotedRefColumn;
			logicalCollectionColumnName = getBuildingContext().getMetadataCollector()
					.getDatabase()
					.getJdbcEnvironment()
					.getIdentifierHelper()
					.toIdentifier( logicalCollectionColumnName, isLogicalColumnQuoted )
					.render();
			getBuildingContext().getMetadataCollector().addColumnNameBinding(
					value.getTable(),
					logicalCollectionColumnName,
					getMappingColumn()
			);
		}
	}
