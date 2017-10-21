	public CorsConfiguration applyPermitDefaultValues() {
		if (this.allowedOrigins == null) {
			this.addAllowedOrigin(ALL);
		}
		if (this.allowedMethods == null) {
			this.setAllowedMethods(Arrays.asList(
					HttpMethod.GET.name(), HttpMethod.HEAD.name(), HttpMethod.POST.name()));
		}
		if (this.allowedHeaders == null) {
			this.addAllowedHeader(ALL);
		}
		if (this.allowCredentials == null) {
			this.setAllowCredentials(true);
		}
		if (this.maxAge == null) {
			this.setMaxAge(1800L);
		}
		return this;
	}
