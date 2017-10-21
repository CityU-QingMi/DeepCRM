	private Path createTempDirectory(ExtensionContext context) {
		try {
			String tempDirName;
			if (context.getTestMethod().isPresent()) {
				tempDirName = context.getTestMethod().get().getName();
			}
			else if (context.getTestClass().isPresent()) {
				tempDirName = context.getTestClass().get().getName();
			}
			else {
				tempDirName = context.getDisplayName();
			}

			return Files.createTempDirectory(tempDirName);
		}
		catch (IOException e) {
			throw new ParameterResolutionException("Could not create temp directory", e);
		}
	}
