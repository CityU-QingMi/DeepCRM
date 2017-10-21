		@Override
		public boolean equals(@Nullable Object obj) {
			if (this == obj) {
				return true;
			}
			if (obj != null && obj instanceof ContentChunkInfo) {
				ContentChunkInfo other = (ContentChunkInfo) obj;
				return (this.start == other.start && this.end == other.end);
			}
			return false;
		}
