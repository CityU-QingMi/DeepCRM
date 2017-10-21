	public static Element addColumn(
			Element parent,
			String name,
			Integer length,
			Integer scale,
			Integer precision,
			String sqlType,
			String customRead,
			String customWrite) {
		return addColumn( parent, name, length, scale, precision, sqlType, customRead, customWrite, false );
	}
