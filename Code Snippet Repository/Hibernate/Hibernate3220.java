		private CustomObjectInputStream(
				InputStream in,
				ClassLoader loader1,
				ClassLoader loader2,
				ClassLoader loader3) throws IOException {
			super( in );
			this.loader1 = loader1;
			this.loader2 = loader2;
			this.loader3 = loader3;
		}
