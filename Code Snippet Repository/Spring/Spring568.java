	public CannotLoadBeanClassException(
			@Nullable String resourceDescription, String beanName, @Nullable String beanClassName, LinkageError cause) {

		super("Error loading class [" + String.valueOf(beanClassName) + "] for bean with name '" + beanName + "'" +
				(resourceDescription != null ? " defined in " + resourceDescription : "") +
				": problem with class file or dependent class", cause);
		this.resourceDescription = resourceDescription;
		this.beanName = beanName;
		this.beanClassName = beanClassName;
	}
