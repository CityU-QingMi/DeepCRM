	private CollectionEntry(
			String role,
			Serializable snapshot,
			Serializable loadedKey,
			SessionFactoryImplementor factory) {
		this.role = role;
		this.snapshot = snapshot;
		this.loadedKey = loadedKey;
		if ( role != null ) {
			afterDeserialize( factory );
		}
	}
