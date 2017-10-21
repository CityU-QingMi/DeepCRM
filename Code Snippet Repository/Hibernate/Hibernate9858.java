	boolean addBasic(
			Element parent,
			PropertyAuditingData propertyAuditingData,
			Value value,
			SimpleMapperBuilder mapper,
			boolean insertable,
			boolean key) {

		if ( value.getType() instanceof BasicType ) {
			if ( parent != null ) {
				final Element propMapping = buildProperty(
						parent,
						propertyAuditingData,
						value,
						insertable,
						key
				);

				if ( isAddNestedType( value ) ) {
					applyNestedType( (SimpleValue) value, propMapping );
				}
			}

			// A null mapper means that we only want to add xml mappings
			if ( mapper != null ) {
				mapper.add( propertyAuditingData.getPropertyData() );
			}

			return true;
		}

		return false;
	}
