	public static ColumnNameIterator getColumnNameIterator(final Iterator<Selectable> selectableIterator) {
		return new ColumnNameIterator() {
			public boolean hasNext() {
				return selectableIterator.hasNext();
			}

			public String next() {
				final Selectable next = selectableIterator.next();
				if ( next.isFormula() ) {
					throw new FormulaNotSupportedException();
				}
				return ( (Column) next ).getName();
			}

			public void remove() {
				selectableIterator.remove();
			}
		};
	}
