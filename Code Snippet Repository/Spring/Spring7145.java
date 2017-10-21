	@Override
	public int compareTo(DestinationPatternsMessageCondition other, Message<?> message) {
		String destination = (String) message.getHeaders().get(LOOKUP_DESTINATION_HEADER);
		if (destination == null) {
			return 0;
		}
		Comparator<String> patternComparator = this.pathMatcher.getPatternComparator(destination);

		Iterator<String> iterator = this.patterns.iterator();
		Iterator<String> iteratorOther = other.patterns.iterator();
		while (iterator.hasNext() && iteratorOther.hasNext()) {
			int result = patternComparator.compare(iterator.next(), iteratorOther.next());
			if (result != 0) {
				return result;
			}
		}
		if (iterator.hasNext()) {
			return -1;
		}
		else if (iteratorOther.hasNext()) {
			return 1;
		}
		else {
			return 0;
		}
	}
