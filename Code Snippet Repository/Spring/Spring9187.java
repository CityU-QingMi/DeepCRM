	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		if (this.type != null) {
			sb.append(this.type);
		}
		if (this.name != null) {
			sb.append("; name=\"");
			sb.append(this.name).append('\"');
		}
		if (this.filename != null) {
			if(this.charset == null || StandardCharsets.US_ASCII.equals(this.charset)) {
				sb.append("; filename=\"");
				sb.append(this.filename).append('\"');
			}
			else {
				sb.append("; filename*=");
				sb.append(encodeHeaderFieldParam(this.filename, this.charset));
			}
		}
		if (this.size != null) {
			sb.append("; size=");
			sb.append(this.size);
		}
		if (this.creationDate != null) {
			sb.append("; creation-date=\"");
			sb.append(RFC_1123_DATE_TIME.format(this.creationDate));
			sb.append('\"');
		}
		if (this.modificationDate != null) {
			sb.append("; modification-date=\"");
			sb.append(RFC_1123_DATE_TIME.format(this.modificationDate));
			sb.append('\"');
		}
		if (this.readDate != null) {
			sb.append("; read-date=\"");
			sb.append(RFC_1123_DATE_TIME.format(this.readDate));
			sb.append('\"');
		}
		return sb.toString();
	}
