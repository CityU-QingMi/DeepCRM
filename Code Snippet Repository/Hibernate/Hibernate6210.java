		@Override
		public boolean equals(Object o) {
			if ( this == o ) {
				return true;
			}
			if ( o == null || getClass() != o.getClass() ) {
				return false;
			}

			PK pk = ( PK ) o;

			if ( brand != null ? !brand.equals( pk.brand ) : pk.brand != null ) {
				return false;
			}
			if ( model != null ? !model.equals( pk.model ) : pk.model != null ) {
				return false;
			}

			return true;
		}
