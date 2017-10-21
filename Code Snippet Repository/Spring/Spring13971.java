	@Override
	@Nullable
	public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
		if (!this.suppressCors && CorsUtils.isCorsRequest(request)) {
			CorsConfiguration config = new CorsConfiguration();
			config.addAllowedOrigin("*");
			config.addAllowedMethod("*");
			config.setAllowCredentials(true);
			config.setMaxAge(ONE_YEAR);
			config.addAllowedHeader("*");
			return config;
		}
		return null;
	}
