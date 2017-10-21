		@Override
		public String format(Identifier catalog, Identifier schema, Identifier name, Dialect dialect) {
			StringBuilder buff = new StringBuilder();

			if ( schema != null ) {
				buff.append( render( schema, dialect ) ).append( '.' );
			}

			buff.append( render( name, dialect ) );

			return buff.toString();
		}
