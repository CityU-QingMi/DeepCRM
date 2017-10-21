	public void params(
			@RequestHeader(name = "name", defaultValue = "bar") String param1,
			@RequestHeader("name") String[] param2,
			@RequestHeader(name = "name", defaultValue="#{systemProperties.systemProperty}") String param3,
			@RequestHeader(name = "name", defaultValue="#{request.contextPath}") String param4,
			@RequestHeader("#{systemProperties.systemProperty}") String param5,
			@RequestHeader("${systemProperty}") String param6,
			@RequestHeader("name") Map<?, ?> unsupported,
			@RequestHeader("name") Date dateParam,
			@RequestHeader("name") Instant instantParam) {
	}
