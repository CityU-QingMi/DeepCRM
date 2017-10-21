	private String determineSubpackageName(Path baseDir, Path classFile) {
		Path relativePath = baseDir.relativize(classFile.getParent());
		String pathSeparator = baseDir.getFileSystem().getSeparator();
		String subpackageName = relativePath.toString().replace(pathSeparator, PACKAGE_SEPARATOR_STRING);
		if (subpackageName.endsWith(pathSeparator)) {
			// Workaround for JDK bug: https://bugs.openjdk.java.net/browse/JDK-8153248
			subpackageName = subpackageName.substring(0, subpackageName.length() - pathSeparator.length());
		}
		return subpackageName;
	}
