	public DefaultWebClientBuilder(DefaultWebClientBuilder other) {
		Assert.notNull(other, "'other' must not be null");

		this.baseUrl = other.baseUrl;
		this.defaultUriVariables = (other.defaultUriVariables != null ?
				new LinkedHashMap<>(other.defaultUriVariables) : null);
		this.uriBuilderFactory = other.uriBuilderFactory;
		if (other.defaultHeaders != null) {
			this.defaultHeaders = new HttpHeaders();
			this.defaultHeaders.putAll(other.defaultHeaders);
		}
		else {
			this.defaultHeaders = null;
		}
		this.defaultCookies = (other.defaultCookies != null ?
				new LinkedMultiValueMap<>(other.defaultCookies) : null);
		this.filters = (other.filters != null ? new ArrayList<>(other.filters) : null);
		this.connector = other.connector;
		this.exchangeStrategies = other.exchangeStrategies;
		this.exchangeFunction = other.exchangeFunction;
	}
