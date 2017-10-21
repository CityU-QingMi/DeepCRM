    protected List<String> parse(String packages) {
        if (packages == null) {
            return Collections.emptyList();
        }
        List<String> pathPrefixes = new ArrayList<>();

        StringTokenizer st = new StringTokenizer(packages, ", \n\t");
        while (st.hasMoreTokens()) {
            String pathPrefix = st.nextToken().replace('.', '/');
            if (!pathPrefix.endsWith("/")) {
                pathPrefix += "/";
            }
            pathPrefixes.add(pathPrefix);
        }

        return pathPrefixes;
    }
