	public static Optional<URL> getLocation(Object object) {
		Preconditions.notNull(object, "object must not be null");
		// determine class loader
		ClassLoader loader = object.getClass().getClassLoader();
		if (loader == null) {
			loader = ClassLoader.getSystemClassLoader();
			while (loader != null && loader.getParent() != null) {
				loader = loader.getParent();
			}
		}
		// try finding resource by name
		if (loader != null) {
			String name = object.getClass().getName();
			name = name.replace(".", "/") + ".class";
			try {
				return Optional.ofNullable(loader.getResource(name));
			}
			catch (Throwable ignore) {
				/* ignore */
			}
		}
		// try protection domain
		try {
			CodeSource codeSource = object.getClass().getProtectionDomain().getCodeSource();
			if (codeSource != null) {
				return Optional.ofNullable(codeSource.getLocation());
			}
		}
		catch (SecurityException ignore) {
			/* ignore */
		}
		return Optional.empty();
	}
