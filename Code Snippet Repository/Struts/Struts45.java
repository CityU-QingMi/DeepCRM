	private List read(InputStream in, int targetLineNumber) {
		List snippet = null;
		if (in != null) {
			snippet = new ArrayList();
			int startLine = 0;
			int endLine = Integer.MAX_VALUE;
			if (targetLineNumber > 0) {
				startLine = targetLineNumber - padding;
				endLine = targetLineNumber + padding;
			}
			try {
				BufferedReader reader = new BufferedReader(new InputStreamReader(in));

				int lineno = 0;
				String line;
				while ((line = reader.readLine()) != null) {
					lineno++;
					if (lineno >= startLine && lineno <= endLine) {
						snippet.add(line);
					}
				}
			} catch (Exception ex) {
				// ignoring as snippet not available isn't a big deal
			}
		}
		return snippet;
	}
