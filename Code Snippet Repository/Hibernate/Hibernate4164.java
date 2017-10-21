	public String[] toColumns(String propertyName) throws QueryException {
		String[] columns = columnsByPropertyPath.get( propertyName );
		if ( columns == null ) {
			throw propertyException( propertyName );
		}
		String[] formulaTemplates = formulaTemplatesByPropertyPath.get( propertyName );
		String[] columnReaders = columnReadersByPropertyPath.get( propertyName );
		String[] result = new String[columns.length];
		for ( int i = 0; i < columns.length; i++ ) {
			if ( columnReaders[i] == null ) {
				result[i] = StringHelper.replace( formulaTemplates[i], Template.TEMPLATE, "" );
			}
			else {
				result[i] = columnReaders[i];
			}
		}
		return result;
	}
