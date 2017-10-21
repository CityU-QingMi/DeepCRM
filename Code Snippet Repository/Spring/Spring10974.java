	@Test
	public void testSupportsAllCharacterEntityReferencesDefinedByHtml() {
		HtmlCharacterEntityReferences entityReferences = new HtmlCharacterEntityReferences();
		Map<Integer, String> referenceCharactersMap = getReferenceCharacterMap();

		for (int character = 0; character < 10000; character++) {
			String referenceName = referenceCharactersMap.get(character);
			if (referenceName != null) {
				String fullReference =
						HtmlCharacterEntityReferences.REFERENCE_START +
						referenceName +
						HtmlCharacterEntityReferences.REFERENCE_END;
				assertTrue("The unicode character " + character + " should be mapped to a reference",
						entityReferences.isMappedToReference((char) character));
				assertEquals("The reference of unicode character " + character + " should be entity " + referenceName,
						fullReference, entityReferences.convertToReference((char) character));
				assertEquals("The entity reference [" + referenceName + "] should be mapped to unicode character " +
						character, (char) character, entityReferences.convertToCharacter(referenceName));
			}
			else if (character == 39) {
				assertTrue(entityReferences.isMappedToReference((char) character));
				assertEquals("&#39;", entityReferences.convertToReference((char) character));
			}
			else {
				assertFalse("The unicode character " + character + " should not be mapped to a reference",
						entityReferences.isMappedToReference((char) character));
				assertNull("No entity reference of unicode character " + character + " should exist",
						entityReferences.convertToReference((char) character));
			}
		}

		assertEquals("The registered entity count of entityReferences should match the number of entity references",
				referenceCharactersMap.size() + 1, entityReferences.getSupportedReferenceCount());
		assertEquals("The HTML 4.0 Standard defines 252+1 entity references so do entityReferences",
				252 + 1, entityReferences.getSupportedReferenceCount());

		assertEquals("Invalid entity reference names should not be convertible",
				(char) -1, entityReferences.convertToCharacter("invalid"));
	}
