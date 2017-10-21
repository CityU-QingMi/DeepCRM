	@SuppressWarnings({ "", "" })
	protected Collection<Object> createCollection(Class<? extends Collection> collectionType, int initialCapacity) {
		if (!collectionType.isInterface()) {
			try {
				return ReflectionUtils.accessibleConstructor(collectionType).newInstance();
			}
			catch (Throwable ex) {
				throw new IllegalArgumentException(
						"Could not instantiate collection class: " + collectionType.getName(), ex);
			}
		}
		else if (List.class == collectionType) {
			return new ArrayList<>(initialCapacity);
		}
		else if (SortedSet.class == collectionType) {
			return new TreeSet<>();
		}
		else {
			return new LinkedHashSet<>(initialCapacity);
		}
	}
