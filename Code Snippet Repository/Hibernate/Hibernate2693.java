	private DereferenceType resolveAsNakedPropertyRef() {
		FromElement fromElement = locateSingleFromElement();
		if (fromElement == null) {
			return DereferenceType.UNKNOWN;
		}
		Queryable persister = fromElement.getQueryable();
		if (persister == null) {
			return DereferenceType.UNKNOWN;
		}
		Type propertyType = getNakedPropertyType(fromElement);
		if (propertyType == null) {
			// assume this ident's text does *not* refer to a property on the given persister
			return DereferenceType.UNKNOWN;
		}

		if ((propertyType.isComponentType() || propertyType.isAssociationType() )) {
			return DereferenceType.COMPONENT_REF;
		}

		setFromElement(fromElement);
		String property = getText();
		String[] columns = getWalker().isSelectStatement()
				? persister.toColumns(fromElement.getTableAlias(), property)
				: persister.toColumns(property);
		String text = StringHelper.join(", ", columns);
		setText(columns.length == 1 ? text : "(" + text + ")");
		setType(SqlTokenTypes.SQL_TOKEN);

		// these pieces are needed for usage in select clause
		super.setDataType(propertyType);
		nakedPropertyRef = true;

		return DereferenceType.PROPERTY_REF;
	}
