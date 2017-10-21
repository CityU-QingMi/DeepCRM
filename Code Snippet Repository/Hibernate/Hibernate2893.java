		@Override
		public boolean equals(Object o) {
			if ( this == o ) {
				return true;
			}
			if ( o == null || getClass() != o.getClass() ) {
				return false;
			}

			BigDecimalHolder that = (BigDecimalHolder) o;

			return this.value == null
					? that.value == null
					: this.value.equals( that.value );
		}
