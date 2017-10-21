	public CompositeData toCompositeData() {
		try {
			return new CompositeDataSupport(
					COMPOSITE_TYPE, ITEM_NAMES, new Object[] {
					name, shortName, loadCount,
					updateCount, insertCount, deleteCount, fetchCount, optimisticFailureCount,
			}
			);
		}
		catch (OpenDataException e) {
			throw new RuntimeException( e );
		}
	}
