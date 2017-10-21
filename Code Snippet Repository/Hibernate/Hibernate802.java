		public NameParts(Identifier catalogName, Identifier schemaName, Identifier objectName) {
			if ( objectName == null ) {
				throw new IllegalArgumentException( "Name cannot be null" );
			}

			this.catalogName = catalogName;
			this.schemaName = schemaName;
			this.objectName = objectName;

			StringBuilder buff = new StringBuilder();
			if ( catalogName != null ) {
				buff.append( catalogName.toString() ).append( '.' );
			}
			if ( schemaName != null ) {
				buff.append( schemaName.toString() ).append( '.' );
			}
			buff.append( objectName.toString() );
			qualifiedText = buff.toString();
		}
