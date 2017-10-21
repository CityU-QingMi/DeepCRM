		final void advance() {
			if ( nextEntry != null && ( nextEntry = nextEntry.next ) != null ) {
				return;
			}

			while ( nextTableIndex >= 0 ) {
				if ( ( nextEntry = currentTable[nextTableIndex--] ) != null ) {
					return;
				}
			}

			while ( nextSegmentIndex >= 0 ) {
				Segment<K, V> seg = segments[nextSegmentIndex--];
				if ( seg.count != 0 ) {
					currentTable = seg.table;
					for ( int j = currentTable.length - 1; j >= 0; --j ) {
						if ( ( nextEntry = currentTable[j] ) != null ) {
							nextTableIndex = j - 1;
							return;
						}
					}
				}
			}
		}
