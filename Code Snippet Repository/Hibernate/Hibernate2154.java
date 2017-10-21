		@Override
		public String format(Identifier catalog, Identifier schema, Identifier name, Dialect dialect) {
			StringBuilder buff = new StringBuilder();

			if ( catalog != null ) {
				buff.append( render( catalog, dialect ) ).append( catalogSeparator );
			}

			buff.append( render( name, dialect ) );

			return buff.toString();
		}
