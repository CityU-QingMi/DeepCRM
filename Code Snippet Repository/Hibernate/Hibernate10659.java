	@Test
	public void testRevisionHistoryPayment() {
		final EmployeeType rev1 = getAuditReader().find( EmployeeType.class, typeId, 1 );
		assertTyping( SalaryEmployeeType.class, rev1 );
		assertEquals( "SALARY", rev1.getType() );
		final EmployeeType rev2 = getAuditReader().find( EmployeeType.class, typeId, 2 );
		assertTyping( SalaryEmployeeType.class, rev2 );
		assertEquals( "SALARY", rev2.getType() );
		final EmployeeType rev3 = getAuditReader().find( EmployeeType.class, typeId, 3 );
		assertTyping( SalaryEmployeeType.class, rev3 );
		assertEquals( "SALARY", rev3.getType() );
	}
