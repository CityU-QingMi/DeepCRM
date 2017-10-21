	@Test
	public void testNonStandardSqlTypeDescriptor() {
		// no override
		SqlTypeDescriptor sqlTypeDescriptor = new IntegerTypeDescriptor() {
			@Override
			public boolean canBeRemapped() {
				return false;
			}
		};
		assertSame( sqlTypeDescriptor, remapSqlTypeDescriptor( sqlTypeDescriptor ) );
	}
