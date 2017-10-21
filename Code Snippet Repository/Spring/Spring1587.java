	private static String getCurrentSubjectName() {
		final AccessControlContext acc = AccessController.getContext();

		return AccessController.doPrivileged(new PrivilegedAction<String>() {

			@Override
			public String run() {
				Subject subject = Subject.getSubject(acc);
				if (subject == null) {
					return null;
				}

				Set<Principal> principals = subject.getPrincipals();

				if (principals == null) {
					return null;
				}
				for (Principal p : principals) {
					return p.getName();
				}
				return null;
			}
		});
	}
