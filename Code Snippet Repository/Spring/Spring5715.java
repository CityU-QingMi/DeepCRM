	protected boolean nextChildIs(Class<?>... clazzes) {
		if (this.parent != null) {
			SpelNodeImpl[] peers = this.parent.children;
			for (int i = 0, max = peers.length; i < max; i++) {
				if (this == peers[i]) {
					if (i + 1 >= max) {
						return false;
					}
					Class<?> clazz = peers[i + 1].getClass();
					for (Class<?> desiredClazz : clazzes) {
						if (clazz.equals(desiredClazz)) {
							return true;
						}
					}
					return false;
				}
			}
		}
		return false;
	}
