	@Test
	public void testExpectedTableNameComponent1() {
		PersistentClass auditClass = metadata().getEntityBinding(
				COMPONENT_1_AUDIT_JOIN_TABLE_NAME
		);
		assert auditClass != null;
		assert COMPONENT_1_AUDIT_JOIN_TABLE_NAME.equals(
				auditClass.getTable()
						.getName()
		);
	}
