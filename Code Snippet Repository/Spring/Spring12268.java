		@Override
		public boolean equals(Object obj) {
			if (this == obj) {
				return true;
			}
			if (obj instanceof ContentChunkInfo) {
				ContentChunkInfo other = (ContentChunkInfo) obj;
				return (this.start == other.start && this.end == other.end);
			}
			return false;
		}
