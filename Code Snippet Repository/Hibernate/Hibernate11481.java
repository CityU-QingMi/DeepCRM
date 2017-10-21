	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("Tombstone{");
		for (int i = 0; i < data.length; i += 3) {
			if (i != 0) {
				sb.append(", ");
			}
			sb.append(new UUID(data[i + 2], data[i + 1])).append('=').append(data[i]);
		}
		sb.append('}');
		return sb.toString();
	}
