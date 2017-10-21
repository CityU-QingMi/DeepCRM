    private String[] readFile(InputStream s) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(s));
        List lines = new ArrayList();
        String line;

        while ( (line = reader.readLine()) != null ) {
            lines.add(line);
        }

        return (String[]) lines.toArray( new String[lines.size()] );
    }
