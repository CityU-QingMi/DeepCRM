	private TestPropertySourceAttributes(Class<?> declaringClass, String[] locations, boolean inheritLocations,
			String[] properties, boolean inheritProperties) {

		Assert.notNull(declaringClass, "declaringClass must not be null");
		if (ObjectUtils.isEmpty(locations) && ObjectUtils.isEmpty(properties)) {
			locations = new String[] { detectDefaultPropertiesFile(declaringClass) };
		}
		this.declaringClass = declaringClass;
		this.locations = locations;
		this.inheritLocations = inheritLocations;
		this.properties = properties;
		this.inheritProperties = inheritProperties;
	}
