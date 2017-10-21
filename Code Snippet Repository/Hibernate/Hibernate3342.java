	public UnsupportedOrmXsdVersionException(String requestedVersion, Origin origin) {
		super(
				String.format(
						"Encountered unsupported orm.xml xsd version [%s] in mapping document [type=%s, name=%s]",
						requestedVersion,
						origin.getType(),
						origin.getName()
				)
		);
	}
