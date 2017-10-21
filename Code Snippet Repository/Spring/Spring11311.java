	@Nullable
	private Object getObjectToRender(@Nullable Map<String, ?> model) {
		if (model == null) {
			return null;
		}

		Map<String, ?> result = model.entrySet().stream()
				.filter(this::isMatch)
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

		if (result.isEmpty()) {
			return null;
		}
		else if (result.size() == 1) {
			return result.values().iterator().next();
		}
		else if (this.canWriteMap) {
			return result;
		}
		else {
			throw new IllegalStateException("Multiple matches found: " + result + " but " +
					"Map rendering is not supported by " + getMessageWriter().getClass().getName());
		}
	}
