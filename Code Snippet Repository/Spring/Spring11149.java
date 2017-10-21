		protected void writeStatusAndHeaders(ServerHttpResponse response) {
			response.setStatusCode(this.statusCode);
			HttpHeaders responseHeaders = response.getHeaders();

			HttpHeaders headers = headers();
			if (!headers.isEmpty()) {
				headers.entrySet().stream()
						.filter(entry -> !responseHeaders.containsKey(entry.getKey()))
						.forEach(entry -> responseHeaders
								.put(entry.getKey(), entry.getValue()));
			}
		}
