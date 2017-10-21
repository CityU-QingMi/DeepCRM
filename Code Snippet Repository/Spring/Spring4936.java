	@Override
	@Nullable
	public XMLEvent peek() {
		if (this.cursor < this.events.size()) {
			return this.events.get(this.cursor);
		}
		else {
			return null;
		}
	}
