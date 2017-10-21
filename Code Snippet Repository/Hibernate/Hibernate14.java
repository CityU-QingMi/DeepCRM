		@Override
		public boolean equals(Object o) {
			if ( this == o ) {
				return true;
			}
			if ( o == null || getClass() != o.getClass() ) {
				return false;
			}
			PersonAddressId that = (PersonAddressId) o;
			return Objects.equals( personId, that.personId ) &&
					Objects.equals( addressId, that.addressId );
		}
