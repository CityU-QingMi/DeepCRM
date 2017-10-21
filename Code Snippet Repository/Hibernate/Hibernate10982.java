		@Override
		public boolean equals(Object o) {
			if ( this == o ) {
				return true;
			}
			if ( o == null || getClass() != o.getClass() ) {
				return false;
			}

			DesignContractId that = (DesignContractId) o;

			if ( contract != null ? !contract.equals( that.contract ) : that.contract != null ) {
				return false;
			}
			return design != null ? design.equals( that.design ) : that.design == null;
		}
