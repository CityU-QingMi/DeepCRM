	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}

		DataMetaPoint dataPoint = (DataMetaPoint) o;

		if (meta != null ? !meta.equals(dataPoint.meta) : dataPoint.meta != null) {
			return false;
		}
		if (dataPoint != null ? !dataPoint.equals(dataPoint.dataPoint) : dataPoint.dataPoint != null) {
			return false;
		}

		return true;
	}
