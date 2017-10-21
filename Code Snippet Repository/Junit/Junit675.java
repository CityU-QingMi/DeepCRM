	default Optional<String> getVersion() {
		Optional<String> standalone = PackageUtils.getAttribute(getClass(), "Engine-Version-" + getId());
		if (standalone.isPresent()) {
			return standalone;
		}
		String fallback = "DEVELOPMENT";
		if (ReflectionUtils.isJavaPlatformModuleSystemAvailable()) {
			String[] getters = { "getModule", "getDescriptor", "rawVersion" };
			return ReflectionUtils.invokeGetters(Optional.of(fallback), getClass(), getters);
		}
		return Optional.of(PackageUtils.getAttribute(getClass(), Package::getImplementationVersion).orElse(fallback));
	}
