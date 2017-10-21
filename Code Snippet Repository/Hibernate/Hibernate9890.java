	public static Element addOrModifyColumn(Element parent, String name) {
		final Element columnMapping = parent.element( "column" );

		if ( columnMapping == null ) {
			return addColumn( parent, name, null, null, null, null, null, null );
		}

		if ( !StringTools.isEmpty( name ) ) {
			addOrModifyAttribute( columnMapping, "name", name );
		}

		return columnMapping;
	}
