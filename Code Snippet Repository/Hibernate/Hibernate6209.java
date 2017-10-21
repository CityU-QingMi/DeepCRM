		@Override
		public boolean equals(Object o) {
			if ( this == o ) {
				return true;
			}
			if ( o == null || getClass() != o.getClass() ) {
				return false;
			}

			PK pk = ( PK ) o;

			if ( classification != null ? !classification.equals( pk.classification ) : pk.classification != null ) {
				return false;
			}
			if ( type != null ? !type.equals( pk.type ) : pk.type != null ) {
				return false;
			}

			return true;
		}
