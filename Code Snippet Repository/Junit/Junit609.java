	public static Optional<String> getAttribute(Class<?> type, String name) {
		Preconditions.notNull(type, "type must not be null");
		Preconditions.notBlank(name, "name must not be blank");
		try {
			CodeSource codeSource = type.getProtectionDomain().getCodeSource();
			URL jarUrl = codeSource.getLocation();
			try (JarFile jarFile = new JarFile(new File(jarUrl.toURI()))) {
				Manifest manifest = jarFile.getManifest();
				Attributes mainAttributes = manifest.getMainAttributes();
				return Optional.ofNullable(mainAttributes.getValue(name));
			}
		}
		catch (Exception e) {
			return Optional.empty();
		}
	}
