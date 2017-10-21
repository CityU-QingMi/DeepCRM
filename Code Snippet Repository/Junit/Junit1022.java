	@Test
	void excludePackageChecksPreconditions() {
		assertThatThrownBy(() -> PackageNameFilter.excludePackageNames((String[]) null)) //
				.isInstanceOf(PreconditionViolationException.class) //
				.hasMessage("packageNames must not be null or empty");
		assertThatThrownBy(() -> PackageNameFilter.excludePackageNames(new String[0])) //
				.isInstanceOf(PreconditionViolationException.class) //
				.hasMessage("packageNames must not be null or empty");
		assertThatThrownBy(() -> PackageNameFilter.excludePackageNames(new String[] { null })) //
				.isInstanceOf(PreconditionViolationException.class) //
				.hasMessage("packageNames must not contain null elements");
	}
