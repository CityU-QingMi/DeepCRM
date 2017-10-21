	private Object[] toResultRow(Object[] row) {
		if ( resultRowLength == row.length ) {
			return row;
		}
		else {
			Object[] result = new Object[resultRowLength];
			int j = 0;
			for ( int i = 0; i < row.length; i++ ) {
				if ( includeInResultRow[i] ) {
					result[j++] = row[i];
				}
			}
			return result;
		}
	}
