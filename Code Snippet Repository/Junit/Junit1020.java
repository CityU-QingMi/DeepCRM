	@Test
	void includePackageChecksPreconditions() {
		assertThatThrownBy(() -> PackageNameFilter.includePackageNames((String[]) null)) //
				.isInstanceOf(PreconditionViolationException.class) //
				.hasMessage("packageNames must not be null or empty");
		assertThatThrownBy(() -> PackageNameFilter.includePackageNames(new String[0])) //
				.isInstanceOf(PreconditionViolationException.class) //
				.hasMessage("packageNames must not be null or empty");
		assertThatThrownBy(() -> PackageNameFilter.includePackageNames(new String[] { null })) //
				.isInstanceOf(PreconditionViolationException.class) //
				.hasMessage("packageNames must not contain null elements");
	}
