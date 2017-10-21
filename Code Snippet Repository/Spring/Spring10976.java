	private Map<Integer, String> getReferenceCharacterMap() {
		CharacterEntityResourceIterator entityIterator = new CharacterEntityResourceIterator();
		Map<Integer, String> referencedCharactersMap = new HashMap<>();
		while (entityIterator.hasNext()) {
			int character = entityIterator.getReferredCharacter();
			String entityName = entityIterator.nextEntry();
			referencedCharactersMap.put(new Integer(character), entityName);
		}
		return referencedCharactersMap;
	}
