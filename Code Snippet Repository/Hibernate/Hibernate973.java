	protected RootEntitySourceImpl(
			MappingDocument sourceMappingDocument,
			JaxbHbmRootEntityType entityElement) {
		super( sourceMappingDocument, entityElement );
		this.primaryTable = Helper.createTableSource(
				sourceMappingDocument(),
				entityElement,
				this,
				entityElement.getRowid(),
				entityElement.getComment(),
				entityElement.getCheck()
		);
		afterInstantiation();
	}
