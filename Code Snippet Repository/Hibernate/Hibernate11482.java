	public Tombstone merge(Tombstone update) {
		assert update != null;
		assert update.data.length == 3;
		int toRemove = 0;
		for (int i = 0; i < data.length; i += 3) {
			if (data[i] < update.data[0]) {
				toRemove += 3;
			}
			else if (update.data[1] == data[i + 1] && update.data[2] == data[i + 2]) {
				// UUID matches - second update during retry?
				toRemove += 3;
			}
		}
		if (data.length == toRemove) {
			// applying the update second time?
			return update;
		}
		else {
			long[] newData = new long[data.length - toRemove + 3]; // 3 for the update
			int j = 0;
			boolean uuidMatch = false;
			for (int i = 0; i < data.length; i += 3) {
				if (data[i] < update.data[0]) {
					// This is an old eviction
					continue;
				}
				else if (update.data[1] == data[i + 1] && update.data[2] == data[i + 2]) {
					// UUID matches
					System.arraycopy(update.data, 0, newData, j, 3);
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
				System.arraycopy(update.data, 0, newData, j, 3);
			}
			return new Tombstone(newData);
		}
	}
