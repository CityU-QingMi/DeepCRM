		@Override
		public boolean equals(Object object) {
			if ( this == object ) {
				return true;
			}
			if ( object == null || getClass() != object.getClass() ) {
				return false;
			}

			Item that = (Item) object;
			if ( name != null ? !name.equals( that.name ) : that.name != null ) {
				return false;
			}
			return !( value != null ? !value.equals( that.value ) : that.value != null );
		}
