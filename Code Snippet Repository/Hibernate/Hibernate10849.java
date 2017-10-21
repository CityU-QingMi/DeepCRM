	@Test
	public void testJoinColumnNamesComponent1() {
		PersistentClass auditClass = metadata().getEntityBinding(
				COMPONENT_1_AUDIT_JOIN_TABLE_NAME
		);
		assert auditClass != null;

		@SuppressWarnings({"unchecked"})
		Iterator<Column> columns = auditClass.getTable().getColumnIterator();

		boolean id1Found = false;
		boolean id2Found = false;

		while ( columns.hasNext() ) {
			Column column = columns.next();
			if ( "VJTRCTE1_ID".equals( column.getName() ) ) {
				id1Found = true;
			}

			if ( "VJTRTE_ID".equals( column.getName() ) ) {
				id2Found = true;
			}
		}

		assert id1Found && id2Found;
	}
