	public static String[][] generateColumnNames(Type[] types, SessionFactoryImplementor f) throws MappingException {
		String[][] columnNames = new String[types.length][];
		for ( int i = 0; i < types.length; i++ ) {
			int span = types[i].getColumnSpan( f );
			columnNames[i] = new String[span];
			for ( int j = 0; j < span; j++ ) {
				columnNames[i][j] = NameGenerator.scalarName( i, j );
			}
		}
		return columnNames;
	}
