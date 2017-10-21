	protected String getTableNameForLogging(Column column) {
		if ( getTable() != null ) {
			if ( getTable().getNameIdentifier() != null ) {
				return getTable().getNameIdentifier().getCanonicalName();
			}
			else {
				return "<unknown>";
			}
		}
		else if ( column.getValue() != null && column.getValue().getTable() != null ) {
			return column.getValue().getTable().getNameIdentifier().getCanonicalName();
		}
		return "<unknown>";
	}
