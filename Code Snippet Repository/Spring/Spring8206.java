	@Override
	public void setAttribute(String name, @Nullable Object value) {
		Assert.notNull(name, "Name must not be null");
		synchronized (this.attributes) {
			if (value != null) {
				this.attributes.put(name, value);
			}
			else {
				this.attributes.remove(name);
			}
		}
	}
