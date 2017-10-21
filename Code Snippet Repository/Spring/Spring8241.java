	@Override
	public String toString() {
		return new ToStringCreator(this)
				.append("testClass", getTestClass())
				.append("locations", ObjectUtils.nullSafeToString(getLocations()))
				.append("classes", ObjectUtils.nullSafeToString(getClasses()))
				.append("contextInitializerClasses", ObjectUtils.nullSafeToString(getContextInitializerClasses()))
				.append("activeProfiles", ObjectUtils.nullSafeToString(getActiveProfiles()))
				.append("propertySourceLocations", ObjectUtils.nullSafeToString(getPropertySourceLocations()))
				.append("propertySourceProperties", ObjectUtils.nullSafeToString(getPropertySourceProperties()))
				.append("contextCustomizers", getContextCustomizers())
				.append("resourceBasePath", getResourceBasePath())
				.append("contextLoader", nullSafeClassName(getContextLoader()))
				.append("parent", getParent())
				.toString();
	}
