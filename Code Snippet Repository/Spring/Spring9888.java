	UriComponentsBuilder adaptFromForwardedHeaders(HttpHeaders headers) {
		String forwardedHeader = headers.getFirst("Forwarded");
		if (StringUtils.hasText(forwardedHeader)) {
			String forwardedToUse = StringUtils.tokenizeToStringArray(forwardedHeader, ",")[0];
			Matcher matcher = FORWARDED_HOST_PATTERN.matcher(forwardedToUse);
			if (matcher.find()) {
				adaptForwardedHost(matcher.group(1).trim());
			}
			matcher = FORWARDED_PROTO_PATTERN.matcher(forwardedToUse);
			if (matcher.find()) {
				scheme(matcher.group(1).trim());
			}
		}
		else {
			String hostHeader = headers.getFirst("X-Forwarded-Host");
			if (StringUtils.hasText(hostHeader)) {
				adaptForwardedHost(StringUtils.tokenizeToStringArray(hostHeader, ",")[0]);
			}

			String portHeader = headers.getFirst("X-Forwarded-Port");
			if (StringUtils.hasText(portHeader)) {
				port(Integer.parseInt(StringUtils.tokenizeToStringArray(portHeader, ",")[0]));
			}

			String protocolHeader = headers.getFirst("X-Forwarded-Proto");
			if (StringUtils.hasText(protocolHeader)) {
				scheme(StringUtils.tokenizeToStringArray(protocolHeader, ",")[0]);
			}
		}

		if ((this.scheme != null) && ((this.scheme.equals("http") && "80".equals(this.port)) ||
				(this.scheme.equals("https") && "443".equals(this.port)))) {
			this.port = null;
		}

		return this;
	}
