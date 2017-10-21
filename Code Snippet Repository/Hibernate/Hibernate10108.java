	private Map<String, Object> mergeModifiedFlags(Map<String, Object> lhs, Map<String, Object> rhs) {
		final ExtendedPropertyMapper mapper = enversService.getEntitiesConfigurations().get( getEntityName() ).getPropertyMapper();
		// Designed to take any lhs modified flag values of true and merge those into the data set for the rhs
		// This makes sure that when merging ModAuditWork with AddWorkUnit within the same transaction for the
		// same entity that the modified flags are tracked correctly.
		for ( PropertyData propertyData : mapper.getProperties().keySet() ) {
			if ( propertyData.isUsingModifiedFlag() && !propertyData.isSynthetic() ) {
				Boolean lhsValue = (Boolean) lhs.get( propertyData.getModifiedFlagPropertyName() );
				if ( lhsValue != null && lhsValue ) {
					Boolean rhsValue = (Boolean) rhs.get( propertyData.getModifiedFlagPropertyName() );
					if ( rhsValue == null || !rhsValue ) {
						rhs.put( propertyData.getModifiedFlagPropertyName(), true );
					}
				}
			}
		}
		return rhs;
	}
