	@Override
	@SuppressWarnings("")
	protected List<Object> createInstance() {
		if (this.sourceList == null) {
			throw new IllegalArgumentException("'sourceList' is required");
		}
		List<Object> result = null;
		if (this.targetListClass != null) {
			result = BeanUtils.instantiateClass(this.targetListClass);
		}
		else {
			result = new ArrayList<>(this.sourceList.size());
		}
		Class<?> valueType = null;
		if (this.targetListClass != null) {
			valueType = ResolvableType.forClass(this.targetListClass).asCollection().resolveGeneric();
		}
		if (valueType != null) {
			TypeConverter converter = getBeanTypeConverter();
			for (Object elem : this.sourceList) {
				result.add(converter.convertIfNecessary(elem, valueType));
			}
		}
		else {
			result.addAll(this.sourceList);
		}
		return result;
	}
