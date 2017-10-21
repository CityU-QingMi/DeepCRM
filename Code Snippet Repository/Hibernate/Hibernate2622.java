		@Override
		public String renderScalarIdentifierSelect(int i) {
			String[] cols = getBasePropertyMapping().toColumns( getTableAlias(), getComponentProperty() );
			StringBuilder buf = new StringBuilder();
			// For property references generate <tablealias>.<columnname> as <projectionalias>
			for ( int j = 0; j < cols.length; j++ ) {
				final String column = cols[j];
				if ( j > 0 ) {
					buf.append( ", " );
				}
				buf.append( column ).append( " as " ).append( NameGenerator.scalarName( i, j ) );
			}
			return buf.toString();
		}
