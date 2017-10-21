	public void addDiscriminatorToSelect(SelectFragment select, String name, String suffix) {
		if ( hasSubclasses() ) {
			if ( explicitDiscriminatorColumnName == null ) {
				select.setExtraSelectList( discriminatorFragment( name ), getDiscriminatorAlias() );
			}
			else {
				if ( getEntityMetamodel().getSuperclass() != null ) {
					name = generateTableAlias( name, getRootHierarchyClassTableIndex() );
				}
				select.addColumn( name, explicitDiscriminatorColumnName, discriminatorAlias );
			}
		}
	}
