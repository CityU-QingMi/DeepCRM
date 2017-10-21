	private void alignColumns(Table referencedTable) {
		final int referencedPkColumnSpan = referencedTable.getPrimaryKey().getColumnSpan();
		if ( referencedPkColumnSpan != getColumnSpan() ) {
			StringBuilder sb = new StringBuilder();
			sb.append( "Foreign key (" ).append( getName() ).append( ":" )
					.append( getTable().getName() )
					.append( " [" );
			appendColumns( sb, getColumnIterator() );
			sb.append( "])" )
					.append( ") must have same number of columns as the referenced primary key (" )
					.append( referencedTable.getName() )
					.append( " [" );
			appendColumns( sb, referencedTable.getPrimaryKey().getColumnIterator() );
			sb.append( "])" );
			throw new MappingException( sb.toString() );
		}

		Iterator fkCols = getColumnIterator();
		Iterator pkCols = referencedTable.getPrimaryKey().getColumnIterator();
		while ( pkCols.hasNext() ) {
			( (Column) fkCols.next() ).setLength( ( (Column) pkCols.next() ).getLength() );
		}

	}
