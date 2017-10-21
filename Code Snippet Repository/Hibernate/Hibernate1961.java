	public static String getLHSTableName(
			AssociationType type,
			int propertyIndex,
			OuterJoinLoadable lhsPersister) {
		if ( type.useLHSPrimaryKey() || propertyIndex < 0 ) {
			return lhsPersister.getTableName();
		}
		else {
			final String propertyName = type.getLHSPropertyName();
			if ( propertyName == null ) {
				//if there is no property-ref, assume the join
				//is to the subclass table (ie. the table of the
				//subclass that the association belongs to)
				return lhsPersister.getSubclassPropertyTableName( propertyIndex );
			}
			else {
				//handle a property-ref
				String propertyRefTable = lhsPersister.getPropertyTableName( propertyName );
				if ( propertyRefTable == null ) {
					//it is possible that the tree-walking in OuterJoinLoader can get to
					//an association defined by a subclass, in which case the property-ref
					//might refer to a property defined on a subclass of the current class
					//in this case, the table name is not known - this temporary solution 
					//assumes that the property-ref refers to a property of the subclass
					//table that the association belongs to (a reasonable guess)
					//TODO: fix this, add: OuterJoinLoadable.getSubclassPropertyTableName(String propertyName)
					propertyRefTable = lhsPersister.getSubclassPropertyTableName( propertyIndex );
				}
				return propertyRefTable;
			}
		}
	}
