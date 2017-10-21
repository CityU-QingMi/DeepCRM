		@Override
		public boolean equals(Object o) {
			if ( this == o ) {
				return true;
			}
			if ( o == null || getClass() != o.getClass() ) {
				return false;
			}
			MobilePhone that = (MobilePhone) o;
			return Objects.equals( countryCode, that.countryCode ) &&
					Objects.equals( operatorCode, that.operatorCode ) &&
					Objects.equals( subscriberCode, that.subscriberCode );
		}
