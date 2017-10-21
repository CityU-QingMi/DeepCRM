	protected SubclassEntitySourceImpl(
			MappingDocument sourceMappingDocument,
			JaxbHbmEntityBaseDefinition entityElement,
			EntitySource container) {
		super( sourceMappingDocument, entityElement );
		this.container = container;

		this.primaryTable = TableInformationContainer.class.isInstance( entityElement )
				? Helper.createTableSource( sourceMappingDocument(), (TableInformationContainer) entityElement, this )
				: null;

		afterInstantiation();
	}
