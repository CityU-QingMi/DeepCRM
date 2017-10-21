	private void processExpression(ColumnTransformer annotation) {
		String nonNullLogicalColumnName = logicalColumnName != null ? logicalColumnName : ""; //use the default for annotations
		if ( annotation != null &&
				( StringHelper.isEmpty( annotation.forColumn() )
						|| annotation.forColumn().equals( nonNullLogicalColumnName ) ) ) {
			readExpression = annotation.read();
			if ( StringHelper.isEmpty( readExpression ) ) {
				readExpression = null;
			}
			writeExpression = annotation.write();
			if ( StringHelper.isEmpty( writeExpression ) ) {
				writeExpression = null;
			}
		}
	}
