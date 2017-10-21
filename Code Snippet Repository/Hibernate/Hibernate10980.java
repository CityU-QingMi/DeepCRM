		@Override
		public boolean equals(Object o) {
			if ( this == o ) {
				return true;
			}
			if ( o == null || getClass() != o.getClass() ) {
				return false;
			}

			Contract contract = (Contract) o;

			return id != null ? id.equals( contract.id ) : contract.id == null;
		}
