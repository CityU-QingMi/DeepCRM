		@Override
		@SuppressWarnings("")
		public boolean equals(Object o) {
			if ( this == o ) {
				return true;
			}
			if ( o == null || getClass() != o.getClass() ) {
				return false;
			}

			NameParts that = (NameParts) o;

			return EqualsHelper.equals( this.getCatalogName(), that.getCatalogName() )
					&& EqualsHelper.equals( this.getSchemaName(), that.getSchemaName() )
					&& EqualsHelper.equals( this.getObjectName(), that.getObjectName() );
		}
