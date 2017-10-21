	private List<Method> findCandidateWriteMethods(MethodDescriptor[] methodDescriptors) {
		List<Method> matches = new ArrayList<>();
		for (MethodDescriptor methodDescriptor : methodDescriptors) {
			Method method = methodDescriptor.getMethod();
			if (isCandidateWriteMethod(method)) {
				matches.add(method);
			}
		}
		// Sort non-void returning write methods to guard against the ill effects of
		// non-deterministic sorting of methods returned from Class#getDeclaredMethods
		// under JDK 7. See http://bugs.sun.com/view_bug.do?bug_id=7023180
		matches.sort((m1, m2) -> m2.toString().compareTo(m1.toString()));
		return matches;
	}
