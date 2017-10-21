	public static Element addColumn(
			Element parent,
			String name,
			Integer length,
			Integer scale,
			Integer precision,
			String sqlType,
			String customRead,
			String customWrite,
			boolean quoted) {
		final Element columnMapping = parent.addElement( "column" );

		columnMapping.addAttribute( "name", quoted ? "`" + name + "`" : name );
		if ( length != null ) {
			columnMapping.addAttribute( "length", length.toString() );
		}
		if ( scale != null ) {
			columnMapping.addAttribute( "scale", Integer.toString( scale ) );
		}
		if ( precision != null ) {
			columnMapping.addAttribute( "precision", Integer.toString( precision ) );
		}
		if ( !StringTools.isEmpty( sqlType ) ) {
			columnMapping.addAttribute( "sql-type", sqlType );
		}

		if ( !StringTools.isEmpty( customRead ) ) {
			columnMapping.addAttribute( "read", customRead );
		}
		if ( !StringTools.isEmpty( customWrite ) ) {
			columnMapping.addAttribute( "write", customWrite );
		}

		return columnMapping;
	}
