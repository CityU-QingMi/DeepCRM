	private static int hash(int h) {
		// Spread bits to regularize both segment and index locations,
		// using variant of single-word Wang/Jenkins hash.
		h += h << 15 ^ 0xffffcd7d;
		h ^= h >>> 10;
		h += h << 3;
		h ^= h >>> 6;
		h += ( h << 2 ) + ( h << 14 );
		return h ^ h >>> 16;
	}
