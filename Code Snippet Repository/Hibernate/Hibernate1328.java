	@Override
	public void doSecondPass(Map persistentClasses) throws MappingException {
		if ( columns != null ) {
			for ( int i = 0; i < columns.length; i++ ) {
				addConstraintToColumn( columns[i] );
			}
		}
		if ( column != null ) {
			this.table = column.getTable();

			final PropertyHolder propertyHolder = column.getPropertyHolder();

			String entityName = ( propertyHolder.isComponent() ) ?
					propertyHolder.getPersistentClass().getEntityName() :
					propertyHolder.getEntityName();

			final PersistentClass persistentClass = (PersistentClass) persistentClasses.get( entityName );
			final Property property = persistentClass.getProperty( column.getPropertyName() );

			if ( property.getValue() instanceof Component ) {
				final Component component = (Component) property.getValue();

				List<Column> columns = new ArrayList<>();
				component.getColumnIterator().forEachRemaining( selectable -> {
					if ( selectable instanceof Column ) {
						columns.add( (Column) selectable );
					}
				} );
				addConstraintToColumns( columns );
			}
			else {
				addConstraintToColumn(
						buildingContext.getMetadataCollector()
								.getLogicalColumnName( table, column.getMappingColumn().getQuotedName() )
				);
			}
		}
	}
