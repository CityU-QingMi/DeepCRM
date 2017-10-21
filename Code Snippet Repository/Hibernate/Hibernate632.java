	private static Member toMember(XProperty xProperty) {
		try {
			return HCANNHelper.getUnderlyingMember( xProperty );
		}
		catch (Exception e) {
			throw new HibernateException(
					"Could not resolve member signature from XProperty reference",
					e
			);
		}
	}
