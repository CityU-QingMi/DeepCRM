	public CompositeData toCompositeData() {
		try {
			return new CompositeDataSupport(
					COMPOSITE_TYPE, ITEM_NAMES, new Object[] {
					getRegion(), getShortName(),
					getHitCount(), getMissCount(), getPutCount(), getHitRatio(), getElementCountInMemory(),
					getElementCountOnDisk(), getElementCountTotal(),
			}
			);
		}
		catch (OpenDataException e) {
			throw new RuntimeException( e );
		}
	}
