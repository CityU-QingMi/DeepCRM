	public ExportableColumn(
			Database database,
			Table table,
			String name,
			BasicType type,
			String dbTypeDeclaration) {
		super( name );
		setValue( new ValueImpl( this, table, type, database ) );
		setSqlType( dbTypeDeclaration );
	}
