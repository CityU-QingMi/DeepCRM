		private HttpHeaders initHeaders() {
			if (CollectionUtils.isEmpty(this.headers)) {
				return (defaultHeaders != null ? defaultHeaders : new HttpHeaders());
			}
			else if (CollectionUtils.isEmpty(defaultHeaders)) {
				return this.headers;
			}
			else {
				HttpHeaders result = new HttpHeaders();
				result.putAll(this.headers);
				defaultHeaders.forEach((name, values) -> {
					if (!this.headers.containsKey(name)) {
						values.forEach(value -> result.add(name, value));
					}
				});
				return result;
			}
		}
