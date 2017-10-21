	public List<Object> matchInParameterValuesWithInsertColumns(Map<String, ?> inParameters) {
		List<Object> values = new ArrayList<>();
		Map<String, Object> source = new LinkedHashMap<>(inParameters.size());
		for (String key : inParameters.keySet()) {
			source.put(key.toLowerCase(), inParameters.get(key));
		}
		for (String column : this.tableColumns) {
			values.add(source.get(column.toLowerCase()));
		}
		return values;
	}
