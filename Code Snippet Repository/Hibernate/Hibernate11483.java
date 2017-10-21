	public Object applyUpdate(UUID uuid, long timestamp, Object value) {
		int toRemove = 0;
		for (int i = 0; i < data.length; i += 3) {
			if (data[i] < timestamp) {
				toRemove += 3;
			}
			else if (uuid.getLeastSignificantBits() == data[i + 1] && uuid.getMostSignificantBits() == data[i + 2]) {
				toRemove += 3;
			}
		}
		if (data.length == toRemove) {
			if (value == null) {
				return new Tombstone(uuid, timestamp);
			}
			else {
				return value;
			}
		}
		else {
			long[] newData = new long[data.length - toRemove + 3]; // 3 for the update
			int j = 0;
			boolean uuidMatch = false;
			for (int i = 0; i < data.length; i += 3) {
				if (data[i] < timestamp) {
					// This is an old eviction
					continue;
				}
				else if (uuid.getLeastSignificantBits() == data[i + 1] && uuid.getMostSignificantBits() == data[i + 2]) {
					newData[j] = timestamp;
					newData[j + 1] = uuid.getLeastSignificantBits();
					newData[j + 2] = uuid.getMostSignificantBits();
					uuidMatch = true;
					j += 3;
				}
				else {
					System.arraycopy(data, i, newData, j, 3);
					j += 3;
				}
			}
			assert (uuidMatch && j == newData.length) || (!uuidMatch && j == newData.length - 3);
			if (!uuidMatch) {
				newData[j] = timestamp;
				newData[j + 1] = uuid.getLeastSignificantBits();
				newData[j + 2] = uuid.getMostSignificantBits();
			}
			return new Tombstone(newData);
		}
	}
