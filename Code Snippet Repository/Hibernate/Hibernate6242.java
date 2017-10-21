		public SqlValueReference[] map(String reference) {
			final String[] columnNames = PROPERTY_MAPPING.toColumns( reference );
			final SqlValueReference[] result = new SqlValueReference[ columnNames.length ];
			int i = 0;
			for ( final String columnName : columnNames ) {
				result[i] = new ColumnReference() {
					@Override
					public String getColumnName() {
						return columnName;
					}
				};
				i++;
			}
			return result;
		}
