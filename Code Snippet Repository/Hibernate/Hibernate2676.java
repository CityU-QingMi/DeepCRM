	public void setQueryableCollection(QueryableCollection queryableCollection) {
		if ( this.queryableCollection != null ) {
			throw new IllegalStateException( "QueryableCollection is already defined for " + this + "!" );
		}
		this.queryableCollection = queryableCollection;
		if ( !queryableCollection.isOneToMany() ) {
			// For many-to-many joins, use the tablename from the queryable collection for the default text.
			fromElement.setText( queryableCollection.getTableName() + " " + getTableAlias() );
		}
	}
