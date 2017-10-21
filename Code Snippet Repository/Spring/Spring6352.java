	@Override
	@Nullable
	public Source getXmlAsSource(ResultSet rs, int columnIndex, @Nullable Class<? extends Source> sourceClass)
			throws SQLException {

		SQLXML xmlObject = rs.getSQLXML(columnIndex);
		if (xmlObject == null) {
			return null;
		}
		return (sourceClass != null ? xmlObject.getSource(sourceClass) : xmlObject.getSource(DOMSource.class));
	}
