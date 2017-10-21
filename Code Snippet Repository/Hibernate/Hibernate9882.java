	public static void addColumn(Element anyMapping, Column column) {
		addColumn(
				anyMapping,
				column.getName(),
				column.getLength(),
				column.getScale(),
				column.getPrecision(),
				column.getSqlType(),
				column.getCustomRead(),
				column.getCustomWrite(),
				column.isQuoted()
		);
	}
