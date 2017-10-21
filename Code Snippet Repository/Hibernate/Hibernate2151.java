		@Override
		public String format(Identifier catalog, Identifier schema, Identifier name, Dialect dialect) {
			StringBuilder buff = new StringBuilder();
			if ( schema != null ) {
				buff.append( render( schema, dialect ) ).append( '.' );
			}

			buff.append( render( name, dialect ) );

			if ( catalog != null ) {
				buff.append( catalogSeparator ).append( render( catalog, dialect ) );
			}

			return buff.toString();
		}
