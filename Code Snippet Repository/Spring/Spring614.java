	@SuppressWarnings({"", ""})
	protected void visitSet(Set setVal) {
		Set newContent = new LinkedHashSet();
		boolean entriesModified = false;
		for (Object elem : setVal) {
			int elemHash = (elem != null ? elem.hashCode() : 0);
			Object newVal = resolveValue(elem);
			int newValHash = (newVal != null ? newVal.hashCode() : 0);
			newContent.add(newVal);
			entriesModified = entriesModified || (newVal != elem || newValHash != elemHash);
		}
		if (entriesModified) {
			setVal.clear();
			setVal.addAll(newContent);
		}
	}
