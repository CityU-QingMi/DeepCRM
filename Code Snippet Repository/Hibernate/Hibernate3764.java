	private String extractDetails(CollectionReference collectionReference) {
		// todo : include some form of parameterized type signature?  i.e., List<String>, Set<Person>, etc
		return String.format(
				"%s(collection=%s, querySpaceUid=%s, path=%s)",
				collectionReference.getClass().getSimpleName(),
				collectionReference.getCollectionPersister().getRole(),
				collectionReference.getQuerySpaceUid(),
				collectionReference.getPropertyPath().getFullPath()
		);
	}
