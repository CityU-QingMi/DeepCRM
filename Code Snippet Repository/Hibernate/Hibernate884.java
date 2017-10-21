	public static TableSpecificationSource createTableSource(
			MappingDocument mappingDocument,
			TableInformationContainer tableInformationContainer,
			InLineViewNameInferrer inLineViewNameInferrer,
			String rowId,
			String comment,
			String checkConstraint) {
		if ( StringHelper.isEmpty( tableInformationContainer.getSubselectAttribute() )
				&& StringHelper.isEmpty( tableInformationContainer.getSubselect() ) ) {
			return new TableSourceImpl(
					mappingDocument,
					tableInformationContainer.getSchema(),
					tableInformationContainer.getCatalog(),
					tableInformationContainer.getTable(),
					rowId,
					comment,
					checkConstraint
			);
		}
		else {
			return new InLineViewSourceImpl(
					mappingDocument,
					tableInformationContainer.getSchema(),
					tableInformationContainer.getCatalog(),
					tableInformationContainer.getSubselectAttribute() != null
							? tableInformationContainer.getSubselectAttribute()
							: tableInformationContainer.getSubselect(),
					tableInformationContainer.getTable() == null
							? inLineViewNameInferrer.inferInLineViewName()
							: tableInformationContainer.getTable(),
					comment
			);
		}
	}
