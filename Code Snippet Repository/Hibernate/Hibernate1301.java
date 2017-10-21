	private Ejb3JoinColumn(
			String sqlType,
			String name,
			boolean nullable,
			boolean unique,
			boolean insertable,
			boolean updatable,
			String referencedColumn,
			String secondaryTable,
			Map<String, Join> joins,
			PropertyHolder propertyHolder,
			String propertyName,
			String mappedBy,
			boolean isImplicit,
			MetadataBuildingContext buildingContext) {
		super();
		setImplicit( isImplicit );
		setSqlType( sqlType );
		setLogicalColumnName( name );
		setNullable( nullable );
		setUnique( unique );
		setInsertable( insertable );
		setUpdatable( updatable );
		setExplicitTableName( secondaryTable );
		setPropertyHolder( propertyHolder );
		setJoins( joins );
		setBuildingContext( buildingContext );
		setPropertyName( BinderHelper.getRelativePath( propertyHolder, propertyName ) );
		bind();
		this.referencedColumn = referencedColumn;
		this.mappedBy = mappedBy;
	}
