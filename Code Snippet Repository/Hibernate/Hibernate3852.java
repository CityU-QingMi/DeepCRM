	private void checkColumnDuplication(java.util.Set distinctColumns, Value value)
			throws MappingException {
		final boolean[] insertability = value.getColumnInsertability();
		final boolean[] updatability = value.getColumnUpdateability();
		final Iterator<Selectable> iterator = value.getColumnIterator();
		int i = 0;
		while ( iterator.hasNext() ) {
			Selectable s = iterator.next();
			// exclude formulas and coluns that are not insertable or updatable
			// since these values can be be repeated (HHH-5393)
			if ( !s.isFormula() && ( insertability[i] || updatability[i] ) ) {
				Column col = (Column) s;
				if ( !distinctColumns.add( col.getName() ) ) {
					throw new MappingException(
							"Repeated column in mapping for collection: "
									+ getRole()
									+ " column: "
									+ col.getName()
					);
				}
			}
			i++;
		}
	}
