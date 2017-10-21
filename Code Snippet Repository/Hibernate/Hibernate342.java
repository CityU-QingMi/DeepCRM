		@Override
		public boolean equals(Object o) {
			if ( this == o ) {
				return true;
			}
			if ( o == null || getClass() != o.getClass() ) {
				return false;
			}
			Isbn isbn = (Isbn) o;
			return Objects.equals( isbn10, isbn.isbn10 ) &&
					Objects.equals( isbn13, isbn.isbn13 );
		}
