		@Override
		public boolean equals(Object o) {
			if ( this == o ) {
				return true;
			}
			if ( o == null || getClass() != o.getClass() ) {
				return false;
			}

			Id id = (Id) o;

			return companyId.equals( id.companyId )
					&& personId.equals( id.personId );

		}
