	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		String[] array = StringUtils.delimitedListToStringArray(text, this.separator, this.charsToDelete);
		if (trimValues) {
			array = StringUtils.trimArrayElements(array);
		}
		if (this.emptyArrayAsNull && array.length == 0) {
			setValue(null);
		}
		else {
			setValue(array);
		}
	}
