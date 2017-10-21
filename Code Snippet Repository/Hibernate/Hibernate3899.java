	public Property getVersion() {
		//get direct version or the one from the super mappedSuperclass
		// or the one from the super persistentClass
		Property propagatedVersion = version;
		if (propagatedVersion == null) {
			if ( superMappedSuperclass != null ) {
				propagatedVersion = superMappedSuperclass.getVersion();
			}
			if (propagatedVersion == null && superPersistentClass != null){
				propagatedVersion = superPersistentClass.getVersion();
			}
		}
		return propagatedVersion;
	}
