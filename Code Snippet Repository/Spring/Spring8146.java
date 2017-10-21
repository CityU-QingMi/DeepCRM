	@Override
	public String toString() {
		return new ToStringCreator(this)
				.append("testClass", this.testClass)
				.append("locations", ObjectUtils.nullSafeToString(this.locations))
				.append("classes", ObjectUtils.nullSafeToString(this.classes))
				.append("contextInitializerClasses", ObjectUtils.nullSafeToString(this.contextInitializerClasses))
				.append("activeProfiles", ObjectUtils.nullSafeToString(this.activeProfiles))
				.append("propertySourceLocations", ObjectUtils.nullSafeToString(this.propertySourceLocations))
				.append("propertySourceProperties", ObjectUtils.nullSafeToString(this.propertySourceProperties))
				.append("contextCustomizers", this.contextCustomizers)
				.append("contextLoader", nullSafeClassName(this.contextLoader))
				.append("parent", this.parent)
				.toString();
	}
