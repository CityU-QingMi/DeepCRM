		@Override
		public boolean equals(Object o) {
			if ( this == o ) {
				return true;
			}
			if ( o == null || getClass() != o.getClass() ) {
				return false;
			}

			final Name that = (Name) o;

			return EqualsHelper.equals( this.catalog, that.catalog )
					&& EqualsHelper.equals( this.schema, that.schema );
		}
