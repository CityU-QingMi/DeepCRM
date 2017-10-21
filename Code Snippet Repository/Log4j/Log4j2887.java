    public boolean trim(final int n) {
        final int l = HashCommon.nextPowerOfTwo((int) Math.ceil(n / loadFactor));
        if (l >= n || size > HashCommon.maxFill(l, loadFactor)) {
			return true;
		}
        try {
            rehash(l);
        } catch (final OutOfMemoryError cantDoIt) { // unusual to catch OOME but in this case appropriate
            return false;
        }
        return true;
    }
