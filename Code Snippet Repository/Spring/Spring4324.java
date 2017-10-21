	@Nullable
	public ReactiveAdapter getAdapter(@Nullable Class<?> reactiveType, @Nullable Object source) {
		Object sourceToUse = (source instanceof Optional ? ((Optional<?>) source).orElse(null) : source);
		Class<?> clazz = (sourceToUse != null ? sourceToUse.getClass() : reactiveType);
		if (clazz == null) {
			return null;
		}

		return this.adapters.stream()
				.filter(adapter -> adapter.getReactiveType() == clazz)
				.findFirst()
				.orElseGet(() ->
						this.adapters.stream()
								.filter(adapter -> adapter.getReactiveType().isAssignableFrom(clazz))
								.findFirst()
								.orElse(null));
	}
