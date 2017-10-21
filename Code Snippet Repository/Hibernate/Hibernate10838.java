	@SuppressWarnings({""})
	@Test
	public void testJoinColumnName() {
		Iterator<Column> columns =
				metadata().getEntityBinding( MIDDLE_VERSIONS_ENTITY_NAME ).getTable().getColumnIterator();

		boolean id1Found = false;
		boolean id2Found = false;

		while ( columns.hasNext() ) {
			Column column = columns.next();
			if ( "ID_1".equals( column.getName() ) ) {
				id1Found = true;
			}

			if ( "ID_2".equals( column.getName() ) ) {
				id2Found = true;
			}
		}

		assert id1Found && id2Found;
	}
