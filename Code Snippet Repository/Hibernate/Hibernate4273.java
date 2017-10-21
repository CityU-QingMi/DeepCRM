	public EnhancedGetterMethodImpl(
			Class containerClass,
			String propertyName,
			Field field,
			Method getterMethod) {
		this.containerClass = containerClass;
		this.propertyName = propertyName;
		this.field = field;
		this.getterMethod = getterMethod;
	}
