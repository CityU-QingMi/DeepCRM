		@Override
		public boolean equals(Object object) {
			if ( this == object ) {
				return true;
			}
			if ( object == null | getClass() != object.getClass() ) {
				return false;
			}

			Product that = (Product) object;
			if ( id != null ? !id.equals( that.id ) : that.id != null ) {
				return false;
			}
			if ( name != null ? !name.equals( that.name ) : that.name != null ) {
				return false;
			}
			return !( items != null ? !items.equals( that.items ) : that.items != null );
		}
