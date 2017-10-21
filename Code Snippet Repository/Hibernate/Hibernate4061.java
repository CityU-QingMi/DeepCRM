		@Override
		public SqlValueReference[] map(String reference) {
			final String[] columnNames;
			final String[] formulaTemplates;

			// handle the special "$element$" property name...
			if ( "$element$".equals( reference ) ) {
				columnNames = elementColumnNames;
				formulaTemplates = elementFormulaTemplates;
			}
			else {
				columnNames = elementPropertyMapping.toColumns( reference );
				formulaTemplates = formulaTemplates( reference, columnNames.length );
			}

			final SqlValueReference[] result = new SqlValueReference[ columnNames.length ];
			int i = 0;
			for ( final String columnName : columnNames ) {
				if ( columnName == null ) {
					// if the column name is null, it indicates that this index in the property value mapping is
					// actually represented by a formula.
//					final int propertyIndex = elementPersister.getEntityMetamodel().getPropertyIndex( reference );
					final String formulaTemplate = formulaTemplates[i];
					result[i] = new FormulaReference() {
						@Override
						public String getFormulaFragment() {
							return formulaTemplate;
						}
					};
				}
				else {
					result[i] = new ColumnReference() {
						@Override
						public String getColumnName() {
							return columnName;
						}
					};
				}
				i++;
			}
			return result;
		}
