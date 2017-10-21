	public static Element createJoin(
			Element parent,
			String tableName,
			String schema,
			String catalog) {
		final Element joinMapping = parent.addElement( "join" );

		joinMapping.addAttribute( "table", tableName );

		if ( !StringTools.isEmpty( schema ) ) {
			joinMapping.addAttribute( "schema", schema );
		}

		if ( !StringTools.isEmpty( catalog ) ) {
			joinMapping.addAttribute( "catalog", catalog );
		}

		return joinMapping;
	}
