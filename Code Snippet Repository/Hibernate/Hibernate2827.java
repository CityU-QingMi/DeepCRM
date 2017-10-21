	@Override
	public final void prepare(
			JdbcServices jdbcServices,
			JdbcConnectionAccess connectionAccess,
			MetadataImplementor metadata,
			SessionFactoryOptions sessionFactoryOptions) {
		// build/get Table representation of the bulk-id tables - subclasses need hooks
		// for each:
		// 		handle DDL
		// 		build insert-select
		//		build id-subselect

		final CT context =  buildPreparationContext();

		initialize( metadata.getMetadataBuildingOptions(), sessionFactoryOptions );

		final JdbcEnvironment jdbcEnvironment = jdbcServices.getJdbcEnvironment();

		for ( PersistentClass entityBinding : metadata.getEntityBindings() ) {
			if ( !IdTableHelper.INSTANCE.needsIdTable( entityBinding ) ) {
				continue;
			}

			final String idTableName = jdbcEnvironment.getQualifiedObjectNameFormatter().format(
					determineIdTableName( jdbcEnvironment, entityBinding ),
					jdbcEnvironment.getDialect()
			);
			final Table idTable = new Table();
			idTable.setName( idTableName );
			idTable.setComment( "Used to hold id values for the " + entityBinding.getEntityName() + " entity" );

			Iterator itr = entityBinding.getTable().getPrimaryKey().getColumnIterator();
			while( itr.hasNext() ) {
				Column column = (Column) itr.next();
				idTable.addColumn( column.clone()  );
			}
			augmentIdTableDefinition( idTable );

			final TT idTableInfo = buildIdTableInfo( entityBinding, idTable, jdbcServices, metadata, context );
			idTableInfoMap.put( entityBinding.getEntityName(), idTableInfo );
		}

		finishPreparation( jdbcServices, connectionAccess, metadata, context );
	}
