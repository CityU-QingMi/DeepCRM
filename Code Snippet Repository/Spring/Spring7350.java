	public void setHeader(String name, @Nullable Object value) {
		if (isReadOnly(name)) {
			throw new IllegalArgumentException("'" + name + "' header is read-only");
		}
		verifyType(name, value);
		if (value != null) {
			// Modify header if necessary
			if (!ObjectUtils.nullSafeEquals(value, getHeader(name))) {
				this.modified = true;
				this.headers.getRawHeaders().put(name, value);
			}
		}
		else {
			// Remove header if available
			if (this.headers.containsKey(name)) {
				this.modified = true;
				this.headers.getRawHeaders().remove(name);
			}
		}
	}
