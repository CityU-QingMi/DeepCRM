		@Override
		public String[] getSqlCreateStrings(Index index, Metadata metadata) {
			final JdbcEnvironment jdbcEnvironment = metadata.getDatabase().getJdbcEnvironment();
			final String tableName = jdbcEnvironment.getQualifiedObjectNameFormatter().format(
					index.getTable().getQualifiedTableName(),
					jdbcEnvironment.getDialect()
			);

			final String indexNameForCreation;
			if ( getDialect().qualifyIndexName() ) {
				indexNameForCreation = jdbcEnvironment.getQualifiedObjectNameFormatter().format(
						new QualifiedNameImpl(
								index.getTable().getQualifiedTableName().getCatalogName(),
								index.getTable().getQualifiedTableName().getSchemaName(),
								jdbcEnvironment.getIdentifierHelper().toIdentifier( index.getName() )
						),
						jdbcEnvironment.getDialect()
				);
			}
			else {
				indexNameForCreation = index.getName();
			}

			StringBuilder colBuf = new StringBuilder("");
			boolean first = true;
			Iterator<Column> columnItr = index.getColumnIterator();
			while ( columnItr.hasNext() ) {
				final Column column = columnItr.next();
				if ( first ) {
					first = false;
				}
				else {
					colBuf.append( ", " );
				}
				colBuf.append( ( column.getQuotedName( jdbcEnvironment.getDialect() )) );
			}
			colBuf.append( ")" );

			final StringBuilder buf = new StringBuilder()
					.append( "create index " )
					.append( indexNameForCreation )
					.append(  "(" + colBuf  )
					.append( " on " )
					.append( tableName );

			return new String[] { buf.toString() };
		}
