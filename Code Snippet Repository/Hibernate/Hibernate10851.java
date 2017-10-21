	@Test
	public void testOverrideNotAudited() {
		PersistentClass auditClass = metadata().getEntityBinding(
				VersionsJoinTableRangeComponentTestEntity.class.getName()
						+ "_AUD"
		);
		assert auditClass != null;

		@SuppressWarnings({"unchecked"})
		Iterator<Column> columns = auditClass.getTable().getColumnIterator();

		boolean auditColumn1Found = false;
		boolean auditColumn2Found = false;

		while ( columns.hasNext() ) {
			Column column = columns.next();
			if ( "STR1".equals( column.getName() ) ) {
				auditColumn1Found = true;
			}

			if ( "STR2".equals( column.getName() ) ) {
				auditColumn2Found = true;
			}
		}

		assert auditColumn1Found && !auditColumn2Found;
	}
