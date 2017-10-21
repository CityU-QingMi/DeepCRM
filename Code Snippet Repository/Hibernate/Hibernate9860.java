	@SuppressWarnings({""})
	boolean addManyToOne(
			Element parent,
			PropertyAuditingData propertyAuditingData,
			Value value,
			SimpleMapperBuilder mapper) {
		final Type type = value.getType();

		// A null mapper occurs when adding to composite-id element
		final Element manyToOneElement = parent.addElement( mapper != null ? "many-to-one" : "key-many-to-one" );
		manyToOneElement.addAttribute( "name", propertyAuditingData.getName() );
		manyToOneElement.addAttribute( "class", type.getName() );

		// HHH-11107
		// Use FK hbm magic value 'none' to skip making foreign key constraints between the Envers
		// schema and the base table schema when a @ManyToOne is present in an identifier.
		if ( mapper == null ) {
			manyToOneElement.addAttribute( "foreign-key", "none" );
		}

		MetadataTools.addColumns( manyToOneElement, value.getColumnIterator() );

		// A null mapper means that we only want to add xml mappings
		if ( mapper != null ) {
			mapper.add( propertyAuditingData.getPropertyData() );
		}

		return true;
	}
