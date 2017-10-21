	private WebSocketRequest<ByteBuf> createRequest(URI url, HttpHeaders headers, List<String> protocols) {
		String query = url.getRawQuery();
		String requestUrl = url.getRawPath() + (query != null ? "?" + query : "");
		HttpClientRequest<ByteBuf, ByteBuf> request = getHttpClient(url).createGet(requestUrl);

		if (!headers.isEmpty()) {
			Map<String, List<Object>> map = new HashMap<>(headers.size());
			headers.forEach((key, values) -> map.put(key, new ArrayList<>(headers.get(key))));
			request = request.setHeaders(map);
		}

		return (ObjectUtils.isEmpty(protocols) ? request.requestWebSocketUpgrade() :
				request.requestWebSocketUpgrade().requestSubProtocols(StringUtils.toStringArray(protocols)));
	}
