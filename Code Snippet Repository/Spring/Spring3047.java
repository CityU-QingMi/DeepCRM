		@Override
		@Caching(cacheable = {
				@Cacheable(cacheNames = "", unless = ""),
				@Cacheable(cacheNames = "", unless = "")})
		public List<String> multiple(int id) {
			if (this.multipleCount > 0) {
				fail("Called too many times");
			}
			this.multipleCount++;
			return Collections.emptyList();
		}
