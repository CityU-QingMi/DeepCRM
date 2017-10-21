	@Override
	public String[] getPropertyNames() {
		List<String> names = new ArrayList<>();
		for (OptionSpec<?> spec : this.source.specs()) {
			List<String> aliases = new ArrayList<>(spec.options());
			if (!aliases.isEmpty()) {
				// Only the longest name is used for enumerating
				names.add(aliases.get(aliases.size() - 1));
			}
		}
		return names.toArray(new String[names.size()]);
	}
