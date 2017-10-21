	private void verifyDeclaredIdNotPresentAndIdPresent(IdentifiableType<?> type) {
		assertEquals( "id", type.getId(Long.class).getName() );
		try {
			type.getDeclaredId(Long.class);
			fail("Should not have a declared id");
		}
		catch (IllegalArgumentException e) {
			//success
		}
	}
