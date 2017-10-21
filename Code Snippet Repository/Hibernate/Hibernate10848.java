	@Test
	public void testExpectedTableNameComponent2() {
		PersistentClass auditClass = metadata().getEntityBinding(
				COMPONENT_2_AUDIT_JOIN_TABLE_NAME
		);
		assert auditClass != null;
		assert COMPONENT_2_AUDIT_JOIN_TABLE_NAME.equals(
				auditClass.getTable()
						.getName()
		);
	}
