    @Override
	public int hashCode() {
        int result = 0;
        for (int j = realSize(), i = 0, t = 0; j-- != 0;) {
            while (keys[i] == null) {
                i++;
            }
            if (this != keys[i]) {
                t = keys[i].hashCode();
            }
            if (this != values[i]) {
                t ^= (values[i] == null ? 0 : values[i].hashCode());
            }
            result += t;
            i++;
        }
        // Zero / null keys have hash zero.
        if (containsNullKey) {
            result += (values[arraySize] == null ? 0 : values[arraySize].hashCode());
        }
        return result;
    }
