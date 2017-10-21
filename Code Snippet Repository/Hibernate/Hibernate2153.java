		@Override
		public String format(Identifier catalog, Identifier schema, Identifier name, Dialect dialect) {
			StringBuilder buff = new StringBuilder();

			buff.append( render( name, dialect ) );

			if ( catalog != null ) {
				buff.append( catalogSeparator ).append( render( catalog, dialect ) );
			}

			return buff.toString();
		}
