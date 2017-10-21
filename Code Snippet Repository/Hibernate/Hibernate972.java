	private static ArrayList<String> extractResultColumns(JaxbHbmNativeQueryPropertyReturnType propertyReturnSource) {
		final String column = unquote( propertyReturnSource.getColumn() );
		ArrayList<String> allResultColumns = new ArrayList<String>();
		if ( column != null ) {
			allResultColumns.add( column );
		}
		for ( JaxbHbmNativeQueryPropertyReturnType.JaxbHbmReturnColumn returnColumnSource : propertyReturnSource.getReturnColumn() ) {
			allResultColumns.add( unquote( returnColumnSource.getName() ) );
		}
		return allResultColumns;
	}
