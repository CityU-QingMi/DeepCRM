	@SuppressWarnings({""})
	private void createJoins(PersistentClass pc, Element parent, ClassAuditingData auditingData) {
		final Iterator<Join> joins = pc.getJoinIterator();
		final Map<Join, Element> joinElements = new HashMap<>();
		entitiesJoins.put( pc.getEntityName(), joinElements );

		while ( joins.hasNext() ) {
			Join join = joins.next();

			// Checking if all of the join properties are audited
			if ( !checkPropertiesAudited( join.getPropertyIterator(), auditingData ) ) {
				continue;
			}

			// Determining the table name. If there is no entry in the dictionary, just constructing the table name
			// as if it was an entity (by appending/prepending configured strings).
			final String originalTableName = join.getTable().getName();
			String auditTableName = auditingData.getSecondaryTableDictionary().get( originalTableName );
			if ( auditTableName == null ) {
				auditTableName = verEntCfg.getAuditEntityName( originalTableName );
			}

			final String schema = getSchema( auditingData.getAuditTable().schema(), join.getTable() );
			final String catalog = getCatalog( auditingData.getAuditTable().catalog(), join.getTable() );

			final Element joinElement = MetadataTools.createJoin( parent, auditTableName, schema, catalog );
			joinElements.put( join, joinElement );

			// HHH-8305 - Fix case when join is considered optional.
			if ( join.isOptional() ) {
				joinElement.addAttribute( "optional", "true" );
			}

			// HHH-8305 - Fix case when join is the inverse side of a mapping.
			if ( join.isInverse() ) {
				joinElement.addAttribute( "inverse", "true" );
			}

			final Element joinKey = joinElement.addElement( "key" );
			MetadataTools.addColumns( joinKey, join.getKey().getColumnIterator() );
			MetadataTools.addColumn( joinKey, verEntCfg.getRevisionFieldName(), null, null, null, null, null, null );
		}
	}
