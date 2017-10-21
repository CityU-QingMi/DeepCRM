	public static ColumnNameIterator getColumnNameIterator(final JoinColumn[] joinColumns) {
		return new ColumnNameIterator() {
			int counter;

			public boolean hasNext() {
				return counter < joinColumns.length;
			}

			public String next() {
				return joinColumns[counter++].name();
			}

			public void remove() {
				throw new UnsupportedOperationException();
			}
		};
	}
