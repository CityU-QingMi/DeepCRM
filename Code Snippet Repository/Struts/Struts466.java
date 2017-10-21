    @Override
    public void load(InputStream in) throws IOException {
        Reader reader = new InputStreamReader(in);
        PropertiesReader pr = new PropertiesReader(reader);
        while (pr.nextProperty()) {
            String name = pr.getPropertyName();
            String val = pr.getPropertyValue();
            int line = pr.getLineNumber();
            String desc = convertCommentsToString(pr.getCommentLines());

            Location loc = new LocationImpl(desc, location.getURI(), line, 0);
            setProperty(name, val, loc);
        }
    }
