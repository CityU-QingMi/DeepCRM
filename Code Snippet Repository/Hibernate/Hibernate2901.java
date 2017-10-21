		@Override
		public boolean equals(Object o) {
			if ( this == o ) {
				return true;
			}
			if ( o == null || getClass() != o.getClass() ) {
				return false;
			}

			BigIntegerHolder that = (BigIntegerHolder) o;

			return this.value == null
					? that.value == null
					: value.equals( that.value );
		}
