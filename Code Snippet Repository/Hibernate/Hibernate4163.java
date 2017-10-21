	public String[] toColumns(String alias, String propertyName) throws QueryException {
		//TODO: *two* hashmap lookups here is one too many...
		String[] columns = columnsByPropertyPath.get( propertyName );
		if ( columns == null ) {
			throw propertyException( propertyName );
		}
		String[] formulaTemplates = formulaTemplatesByPropertyPath.get( propertyName );
		String[] columnReaderTemplates = columnReaderTemplatesByPropertyPath.get( propertyName );
		String[] result = new String[columns.length];
		for ( int i = 0; i < columns.length; i++ ) {
			if ( columnReaderTemplates[i] == null ) {
				result[i] = StringHelper.replace( formulaTemplates[i], Template.TEMPLATE, alias );
			}
			else {
				result[i] = StringHelper.replace( columnReaderTemplates[i], Template.TEMPLATE, alias );
			}
		}
		return result;
	}
