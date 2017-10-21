	private Join buildJoinFromMappedBySide(PersistentClass persistentClass, Property otherSideProperty, Join originalJoin) {
		Join join = new Join();
		join.setPersistentClass( persistentClass );

		//no check constraints available on joins
		join.setTable( originalJoin.getTable() );
		join.setInverse( true );
		SimpleValue key = new DependantValue( buildingContext.getMetadataCollector(), join.getTable(), persistentClass.getIdentifier() );
		//TODO support @ForeignKey
		join.setKey( key );
		join.setSequentialSelect( false );
		//TODO support for inverse and optional
		join.setOptional( true ); //perhaps not quite per-spec, but a Good Thing anyway
		key.setCascadeDeleteEnabled( false );
		Iterator mappedByColumns = otherSideProperty.getValue().getColumnIterator();
		while ( mappedByColumns.hasNext() ) {
			Column column = (Column) mappedByColumns.next();
			Column copy = new Column();
			copy.setLength( column.getLength() );
			copy.setScale( column.getScale() );
			copy.setValue( key );
			copy.setName( column.getQuotedName() );
			copy.setNullable( column.isNullable() );
			copy.setPrecision( column.getPrecision() );
			copy.setUnique( column.isUnique() );
			copy.setSqlType( column.getSqlType() );
			copy.setCheckConstraint( column.getCheckConstraint() );
			copy.setComment( column.getComment() );
			copy.setDefaultValue( column.getDefaultValue() );
			key.addColumn( copy );
		}
		persistentClass.addJoin( join );
		return join;
	}
