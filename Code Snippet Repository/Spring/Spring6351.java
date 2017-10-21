	@Override
	@Nullable
	public Source getXmlAsSource(ResultSet rs, String columnName, @Nullable Class<? extends Source> sourceClass)
			throws SQLException {

		SQLXML xmlObject = rs.getSQLXML(columnName);
		if (xmlObject == null) {
			return null;
		}
		return (sourceClass != null ? xmlObject.getSource(sourceClass) : xmlObject.getSource(DOMSource.class));
	}
