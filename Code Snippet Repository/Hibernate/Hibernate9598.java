	@Test
	public void testMutabilityPlan() {
		assertTrue( shouldBeMutable() == typeDescriptor.getMutabilityPlan().isMutable() );

		if ( Clob.class.isInstance( testData.copyOfOriginalValue )
				|| Blob.class.isInstance( testData.copyOfOriginalValue ) ) {
			return;
		}

		T copy = typeDescriptor.getMutabilityPlan().deepCopy( testData.copyOfOriginalValue );
		assertTrue( typeDescriptor.areEqual( copy, testData.copyOfOriginalValue ) );
		if ( ! shouldBeMutable() ) {
			assertTrue( copy == testData.copyOfOriginalValue );
		}

		// ensure the symmetry of assemble/disassebly
		Serializable cached = typeDescriptor.getMutabilityPlan().disassemble( testData.copyOfOriginalValue );
		if ( ! shouldBeMutable() ) {
			assertTrue( cached == testData.copyOfOriginalValue );
		}
		T reassembled = typeDescriptor.getMutabilityPlan().assemble( cached );
		assertTrue( typeDescriptor.areEqual( testData.originalValue, reassembled ) );
	}
