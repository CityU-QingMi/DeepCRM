	@Override
	public String toString() {
		return new ToStringCreator(this)//
		.append("declaringClass", declaringClass.getName())//
		.append("locations", ObjectUtils.nullSafeToString(locations))//
		.append("inheritLocations", inheritLocations)//
		.append("properties", ObjectUtils.nullSafeToString(properties))//
		.append("inheritProperties", inheritProperties)//
		.toString();
	}
