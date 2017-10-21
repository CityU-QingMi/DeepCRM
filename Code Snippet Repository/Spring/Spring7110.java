	private void writeObject(ObjectOutputStream out) throws IOException {
		Set<String> keysToIgnore = new HashSet<>();
		this.headers.forEach((key, value) -> {
			if (!(value instanceof Serializable)) {
				keysToIgnore.add(key);
			}
		});

		if (keysToIgnore.isEmpty()) {
			// All entries are serializable -> serialize the regular MessageHeaders instance
			out.defaultWriteObject();
		}
		else {
			// Some non-serializable entries -> serialize a temporary MessageHeaders copy
			if (logger.isDebugEnabled()) {
				logger.debug("Ignoring non-serializable message headers: " + keysToIgnore);
			}
			out.writeObject(new MessageHeaders(this, keysToIgnore));
		}
	}
