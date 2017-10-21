		@Override
		public boolean equals(Object o) {
			if ( this == o ) {
				return true;
			}
			if ( o == null || getClass() != o.getClass() ) {
				return false;
			}

			Child child = (Child) o;

			if ( id != null ? !id.equals( child.id ) : child.id != null ) {
				return false;
			}
			if ( name != null ? !name.equals( child.name ) : child.name != null ) {
				return false;
			}
			return parents != null ? parents.equals( child.parents ) : child.parents == null;

		}
