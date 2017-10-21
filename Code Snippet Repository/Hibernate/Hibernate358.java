		@Override
		public boolean equals(Object o) {
			if ( this == o ) {
				return true;
			}
			if ( o == null || getClass() != o.getClass() ) {
				return false;
			}
			Publisher publisher = (Publisher) o;
			return Objects.equals( id, publisher.id ) &&
					Objects.equals( name, publisher.name );
		}
