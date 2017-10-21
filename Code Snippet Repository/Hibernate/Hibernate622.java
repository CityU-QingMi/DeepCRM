	private void addMappingReferences(List<MappingReference> mappingReferences) {
		if ( mappingReferences == null ) {
			return;
		}

		if ( this.mappingReferences == null ) {
			this.mappingReferences =  new ArrayList<MappingReference>();
		}
		this.mappingReferences.addAll( mappingReferences );
	}
