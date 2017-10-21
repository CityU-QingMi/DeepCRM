		@Override
		public boolean equals(Object o) {
			if ( this == o ) {
				return true;
			}
			if ( !( o instanceof Item ) ) {
				return false;
			}
			Item item = (Item) o;
			return Objects.equals( getName(), item.getName() );
		}
