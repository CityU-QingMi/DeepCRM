	private void addEndRevision(Element anyMapping) {
		// Add the end-revision field, if the appropriate strategy is used.
		if ( auditStrategy instanceof ValidityAuditStrategy ) {
			final Element endRevMapping = (Element) revisionInfoRelationMapping.clone();
			endRevMapping.setName( "many-to-one" );
			endRevMapping.addAttribute( "name", verEntCfg.getRevisionEndFieldName() );
			MetadataTools.addOrModifyColumn( endRevMapping, verEntCfg.getRevisionEndFieldName() );

			anyMapping.add( endRevMapping );

			if ( verEntCfg.isRevisionEndTimestampEnabled() ) {
				// add a column for the timestamp of the end revision
				final String revisionInfoTimestampSqlType = TimestampType.INSTANCE.getName();
				final Element timestampProperty = MetadataTools.addProperty(
						anyMapping,
						verEntCfg.getRevisionEndTimestampFieldName(),
						revisionInfoTimestampSqlType,
						true,
						true,
						false
				);
				MetadataTools.addColumn(
						timestampProperty,
						verEntCfg.getRevisionEndTimestampFieldName(),
						null,
						null,
						null,
						null,
						null,
						null
				);
			}
		}
	}
