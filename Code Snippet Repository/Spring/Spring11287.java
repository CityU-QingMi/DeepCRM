	@Nullable
	private Object findAndRemoveReactiveAttribute(Model model, String attributeName) {
		return model.asMap().entrySet().stream()
				.filter(entry -> {
					if (!entry.getKey().startsWith(attributeName)) {
						return false;
					}
					ReactiveAdapter adapter = getAdapterRegistry().getAdapter(null, entry.getValue());
					if (adapter == null) {
						return false;
					}
					String name = attributeName + ClassUtils.getShortName(adapter.getReactiveType());
					return entry.getKey().equals(name);
				})
				.findFirst()
				.map(entry -> {
					// Remove since we will be re-inserting the resolved attribute value
					model.asMap().remove(entry.getKey());
					return entry.getValue();
				})
				.orElse(null);
	}
