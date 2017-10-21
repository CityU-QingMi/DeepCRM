	protected void initMappingColumn(
			String columnName,
			String propertyName,
			int length,
			int precision,
			int scale,
			boolean nullable,
			String sqlType,
			boolean unique,
			boolean applyNamingStrategy) {
		if ( StringHelper.isNotEmpty( formulaString ) ) {
			this.formula = new Formula();
			this.formula.setFormula( formulaString );
		}
		else {
			this.mappingColumn = new Column();
			redefineColumnName( columnName, propertyName, applyNamingStrategy );
			this.mappingColumn.setLength( length );
			if ( precision > 0 ) {  //revelent precision
				this.mappingColumn.setPrecision( precision );
				this.mappingColumn.setScale( scale );
			}
			this.mappingColumn.setNullable( nullable );
			this.mappingColumn.setSqlType( sqlType );
			this.mappingColumn.setUnique( unique );

			if(writeExpression != null && !writeExpression.matches("[^?]*\\?[^?]*")) {
				throw new AnnotationException(
						"@WriteExpression must contain exactly one value placeholder ('?') character: property ["
								+ propertyName + "] and column [" + logicalColumnName + "]"
				);
			}
			if ( readExpression != null) {
				this.mappingColumn.setCustomRead( readExpression );
			}
			if ( writeExpression != null) {
				this.mappingColumn.setCustomWrite( writeExpression );
			}
		}
	}
