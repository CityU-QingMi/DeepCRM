	@Override
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof HttpCookie)) {
			return false;
		}
		HttpCookie otherCookie = (HttpCookie) other;
		return (this.name.equalsIgnoreCase(otherCookie.getName()));
	}
