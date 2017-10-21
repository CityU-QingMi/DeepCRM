	@Override
	public int hashCode() {
		int result = Arrays.hashCode(this.locations);
		result = 31 * result + Arrays.hashCode(this.classes);
		result = 31 * result + this.contextInitializerClasses.hashCode();
		result = 31 * result + Arrays.hashCode(this.activeProfiles);
		result = 31 * result + Arrays.hashCode(this.propertySourceLocations);
		result = 31 * result + Arrays.hashCode(this.propertySourceProperties);
		result = 31 * result + this.contextCustomizers.hashCode();
		result = 31 * result + (this.parent != null ? this.parent.hashCode() : 0);
		result = 31 * result + nullSafeClassName(this.contextLoader).hashCode();
		return result;
	}
