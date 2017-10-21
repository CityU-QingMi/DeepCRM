	@Override
	protected Class<?>[] getAnnotatedClasses() {
		return new Class[] {
				Person.class,
				Account.class,
				AccountNotAuditedOwners.class,
				NotAuditedNoProxyPerson.class,
				NotAuditedProxyPerson.class
		};
	}
