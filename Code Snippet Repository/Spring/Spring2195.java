		@Nullable
		public static SpecificPlatform get() {
			ClassLoader classLoader = MBeanExportConfiguration.class.getClassLoader();
			for (SpecificPlatform environment : values()) {
				if (ClassUtils.isPresent(environment.identifyingClass, classLoader)) {
					return environment;
				}
			}
			return null;
		}
