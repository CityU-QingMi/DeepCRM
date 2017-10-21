		public CharacterEntityResourceIterator() {
			try {
				InputStream inputStream = getClass().getResourceAsStream(DTD_FILE);
				if (inputStream == null) {
					throw new IOException("Cannot find definition resource [" + DTD_FILE + "]");
				}
				tokenizer = new StreamTokenizer(new BufferedReader(new InputStreamReader(inputStream, "UTF-8")));
			}
			catch (IOException ex) {
				throw new IllegalStateException("Failed to open definition resource [" + DTD_FILE + "]");
			}
		}
