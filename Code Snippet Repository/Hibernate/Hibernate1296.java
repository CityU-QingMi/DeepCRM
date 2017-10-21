	public Join getJoin() {
		Join join = joins.get( explicitTableName );
		if ( join == null ) {
			// annotation binding seems to use logical and physical naming somewhat inconsistently...
			final String physicalTableName = getBuildingContext().getMetadataCollector().getPhysicalTableName( explicitTableName );
			if ( physicalTableName != null ) {
				join = joins.get( physicalTableName );
			}
		}

		if ( join == null ) {
			throw new AnnotationException(
					"Cannot find the expected secondary table: no "
							+ explicitTableName + " available for " + propertyHolder.getClassName()
			);
		}

		return join;
	}
