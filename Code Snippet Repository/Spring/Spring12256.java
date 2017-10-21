		@Override
		public String addVersion(String path, String version) {
			if (path.startsWith(".")) {
				return path;
			}
			else {
				return (this.prefix.endsWith("/") || path.startsWith("/") ?
						this.prefix + path : this.prefix + '/' + path);
			}
		}
