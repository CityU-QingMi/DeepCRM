	@Test
	@TestForIssue( jiraKey = "")
	public void testOtherPerson() {
		String address = "other-person-super-address";
		String localAddress = "other-person-local-address";

		PersonBaseBase person = createPerson( new OtherPerson(), address, localAddress );

		assertAddress( person, address, localAddress );
	}
