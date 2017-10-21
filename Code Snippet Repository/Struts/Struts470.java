    public List<String> getSnippet(int padding) {
        List<String> snippet = new ArrayList<>();
        if (getLineNumber() > 0) {
            try {
                InputStream in = new URL(getURI()).openStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                
                int lineno = 0;
                int errno = getLineNumber();
                String line;
                while ((line = reader.readLine()) != null) {
                    lineno++;
                    if (lineno >= errno - padding && lineno <= errno + padding) {
                        snippet.add(line);
                    }
                }
            } catch (Exception ex) {
                // ignoring as snippet not available isn't a big deal
            }
        }
        return snippet;
    }
