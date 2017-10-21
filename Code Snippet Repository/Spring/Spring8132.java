	@SuppressWarnings("")
	public static ProfileValueSource retrieveProfileValueSource(Class<?> testClass) {
		Assert.notNull(testClass, "testClass must not be null");

		Class<ProfileValueSourceConfiguration> annotationType = ProfileValueSourceConfiguration.class;
		ProfileValueSourceConfiguration config = AnnotatedElementUtils.findMergedAnnotation(testClass, annotationType);
		if (logger.isDebugEnabled()) {
			logger.debug("Retrieved @ProfileValueSourceConfiguration [" + config + "] for test class [" +
					testClass.getName() + "]");
		}

		Class<? extends ProfileValueSource> profileValueSourceType;
		if (config != null) {
			profileValueSourceType = config.value();
		}
		else {
			profileValueSourceType = (Class<? extends ProfileValueSource>) AnnotationUtils.getDefaultValue(annotationType);
			Assert.state(profileValueSourceType != null, "No default ProfileValueSource class");
		}
		if (logger.isDebugEnabled()) {
			logger.debug("Retrieved ProfileValueSource type [" + profileValueSourceType + "] for class [" +
					testClass.getName() + "]");
		}

		ProfileValueSource profileValueSource;
		if (SystemProfileValueSource.class == profileValueSourceType) {
			profileValueSource = SystemProfileValueSource.getInstance();
		}
		else {
			try {
				profileValueSource = ReflectionUtils.accessibleConstructor(profileValueSourceType).newInstance();
			}
			catch (Exception ex) {
				if (logger.isWarnEnabled()) {
					logger.warn("Could not instantiate a ProfileValueSource of type [" + profileValueSourceType +
							"] for class [" + testClass.getName() + "]: using default.", ex);
				}
				profileValueSource = SystemProfileValueSource.getInstance();
			}
		}

		return profileValueSource;
	}
