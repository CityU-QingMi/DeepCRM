	public static Ejb3JoinColumn buildJoinFormula(
			JoinFormula ann,
			String mappedBy,
			Map<String, Join> joins,
			PropertyHolder propertyHolder,
			String propertyName,
			MetadataBuildingContext buildingContext) {
		Ejb3JoinColumn formulaColumn = new Ejb3JoinColumn();
		formulaColumn.setFormula( ann.value() );
		formulaColumn.setReferencedColumn(ann.referencedColumnName());
		formulaColumn.setBuildingContext( buildingContext );
		formulaColumn.setPropertyHolder( propertyHolder );
		formulaColumn.setJoins( joins );
		formulaColumn.setPropertyName( BinderHelper.getRelativePath( propertyHolder, propertyName ) );
		formulaColumn.bind();
		return formulaColumn;
	}
