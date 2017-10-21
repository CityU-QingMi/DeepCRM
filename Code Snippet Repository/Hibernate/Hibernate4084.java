	public CompositeElementPropertyMapping(
			String[] elementColumns,
			String[] elementColumnReaders,
			String[] elementColumnReaderTemplates, 
			String[] elementFormulaTemplates, 
			CompositeType compositeType,
			Mapping factory)
	throws MappingException {

		this.compositeType = compositeType;

		initComponentPropertyPaths(null, compositeType, elementColumns, elementColumnReaders,
				elementColumnReaderTemplates, elementFormulaTemplates, factory);

	}
