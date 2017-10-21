		@Override
		public boolean equals(Object o) {
			if ( this == o ) {
				return true;
			}
			if ( o == null || getClass() != o.getClass() ) {
				return false;
			}
			PersonAddress that = (PersonAddress) o;
			return Objects.equals( person, that.person ) &&
					Objects.equals( address, that.address );
		}
