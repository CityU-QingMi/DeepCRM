	private Element buildProperty(
			Element parent,
			PropertyAuditingData propertyAuditingData,
			Value value,
			boolean insertable,
			boolean key) {
		final Element propMapping = MetadataTools.addProperty(
				parent,
				propertyAuditingData.getName(),
				isAddNestedType( value ) ? null : getBasicTypeName( value.getType() ),
				propertyAuditingData.isForceInsertable() || insertable,
				key
		);

		MetadataTools.addColumns( propMapping, value.getColumnIterator() );

		return propMapping;
	}
