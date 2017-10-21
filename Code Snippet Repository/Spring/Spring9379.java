	@SuppressWarnings("")
	private void registerWellKnownModulesIfAvailable(ObjectMapper objectMapper) {
		try {
			Class<? extends Module> jdk8Module = (Class<? extends Module>)
					ClassUtils.forName("com.fasterxml.jackson.datatype.jdk8.Jdk8Module", this.moduleClassLoader);
			objectMapper.registerModule(BeanUtils.instantiateClass(jdk8Module));
		}
		catch (ClassNotFoundException ex) {
			// jackson-datatype-jdk8 not available
		}

		try {
			Class<? extends Module> javaTimeModule = (Class<? extends Module>)
					ClassUtils.forName("com.fasterxml.jackson.datatype.jsr310.JavaTimeModule", this.moduleClassLoader);
			objectMapper.registerModule(BeanUtils.instantiateClass(javaTimeModule));
		}
		catch (ClassNotFoundException ex) {
			// jackson-datatype-jsr310 not available
		}

		// Joda-Time present?
		if (ClassUtils.isPresent("org.joda.time.LocalDate", this.moduleClassLoader)) {
			try {
				Class<? extends Module> jodaModule = (Class<? extends Module>)
						ClassUtils.forName("com.fasterxml.jackson.datatype.joda.JodaModule", this.moduleClassLoader);
				objectMapper.registerModule(BeanUtils.instantiateClass(jodaModule));
			}
			catch (ClassNotFoundException ex) {
				// jackson-datatype-joda not available
			}
		}

		// Kotlin present?
		if (KotlinDetector.isKotlinPresent()) {
			try {
				Class<? extends Module> kotlinModule = (Class<? extends Module>)
						ClassUtils.forName("com.fasterxml.jackson.module.kotlin.KotlinModule", this.moduleClassLoader);
				objectMapper.registerModule(BeanUtils.instantiateClass(kotlinModule));
			}
			catch (ClassNotFoundException ex) {
				logger.warn("For Jackson Kotlin classes support please add \"com.fasterxml.jackson.module:jackson-module-kotlin\" to the classpath");
			}
		}
	}
