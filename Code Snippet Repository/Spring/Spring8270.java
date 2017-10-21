	protected String getRequestDetails() {
		StringBuilder sb = new StringBuilder();
		sb.append(this.requests.size()).append(" request(s) executed");
		if (!this.requests.isEmpty()) {
			sb.append(":\n");
			for (ClientHttpRequest request : this.requests) {
				sb.append(request.toString()).append("\n");
			}
		}
		else {
			sb.append(".\n");
		}
		return sb.toString();
	}
