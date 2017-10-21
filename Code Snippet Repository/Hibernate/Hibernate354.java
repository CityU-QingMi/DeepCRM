		@Override
		public boolean equals(Object o) {
			if ( this == o ) {
				return true;
			}
			if ( o == null || getClass() != o.getClass() ) {
				return false;
			}
			Book book = (Book) o;
			return Objects.equals( author, book.author ) &&
					Objects.equals( publisher, book.publisher ) &&
					Objects.equals( title, book.title );
		}
