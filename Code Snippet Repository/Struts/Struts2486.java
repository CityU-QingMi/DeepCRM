    private Test<URL> getResourceTest(final String resultPath, final String actionName) {
        return new Test<URL>() {
            public boolean test(URL url) {
                String urlStr = url.toString();
                int index = urlStr.lastIndexOf(resultPath);
                String path = urlStr.substring(index + resultPath.length());
                return path.startsWith(actionName);
            }
        };
    }
