	public String toString() {
		if ( !isReferenceToPrimaryKey() ) {
			return getClass().getName()
					+ '(' + getTable().getName() + getColumns()
					+ " ref-columns:" + '(' + getReferencedColumns() + ") as " + getName() + ")";
		}
		else {
			return super.toString();
		}

	}
