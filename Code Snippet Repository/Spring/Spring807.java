	private void addCandidateEntry(Map<String, Object> candidates, String candidateName,
			DependencyDescriptor descriptor, Class<?> requiredType) {

		if (descriptor instanceof MultiElementDescriptor || containsSingleton(candidateName)) {
			candidates.put(candidateName, descriptor.resolveCandidate(candidateName, requiredType, this));
		}
		else {
			candidates.put(candidateName, getType(candidateName));
		}
	}
