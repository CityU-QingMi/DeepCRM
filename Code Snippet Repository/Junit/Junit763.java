	private void addChildrenRecursively(VintageTestDescriptor parent) {
		List<Description> children = parent.getDescription().getChildren();
		// Use LinkedHashMap to preserve order, ArrayList for fast access by index
		Map<String, List<Description>> childrenByUniqueId = children.stream().collect(
			groupingBy(uniqueIdReader.andThen(uniqueIdStringifier), LinkedHashMap::new, toCollection(ArrayList::new)));
		for (Entry<String, List<Description>> entry : childrenByUniqueId.entrySet()) {
			String uniqueId = entry.getKey();
			List<Description> childrenWithSameUniqueId = entry.getValue();
			IntFunction<String> uniqueIdGenerator = determineUniqueIdGenerator(uniqueId, childrenWithSameUniqueId);
			for (int index = 0; index < childrenWithSameUniqueId.size(); index++) {
				String reallyUniqueId = uniqueIdGenerator.apply(index);
				Description description = childrenWithSameUniqueId.get(index);
				UniqueId id = parent.getUniqueId().append(VintageTestDescriptor.SEGMENT_TYPE_TEST, reallyUniqueId);
				VintageTestDescriptor child = new VintageTestDescriptor(id, description);
				parent.addChild(child);
				addChildrenRecursively(child);
			}
		}
	}
