	public Iterator sqlCommentStrings(Dialect dialect, String defaultCatalog, String defaultSchema) {
		List comments = new ArrayList();
		if ( dialect.supportsCommentOn() ) {
			String tableName = getQualifiedName( dialect, defaultCatalog, defaultSchema );
			if ( comment != null ) {
				comments.add( "comment on table " + tableName + " is '" + comment + "'" );
			}
			Iterator iter = getColumnIterator();
			while ( iter.hasNext() ) {
				Column column = (Column) iter.next();
				String columnComment = column.getComment();
				if ( columnComment != null ) {
					comments.add( "comment on column " + tableName + '.' + column.getQuotedName( dialect ) + " is '" + columnComment + "'" );
				}
			}
		}
		return comments.iterator();
	}
