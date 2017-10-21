    public void testDefaultExcludePatterns() throws Exception {
        // given
        List<String> prefixes = Arrays.asList("#[0].%s", "[0].%s", "top.%s", "%{[0].%s}", "%{#[0].%s}", "%{top.%s}", "%{#top.%s}", "%{#%s}", "%{%s}", "#%s");
        List<String> inners = Arrays.asList("servletRequest", "servletResponse", "servletContext", "application", "session", "struts", "request", "response", "dojo", "parameters");
        List<String> suffixes = Arrays.asList("['test']", "[\"test\"]", ".test");

        DefaultExcludedPatternsChecker checker = new DefaultExcludedPatternsChecker();
        checker.setAdditionalExcludePatterns(".*(^|\\.|\\[|'|\")class(\\.|\\[|'|\").*");

        List<String> params = new ArrayList<String>();
        for (String prefix : prefixes) {
            for (String inner : inners) {
                String innerUp = inner.substring(0, 1).toUpperCase() + inner.substring(1);
                for (String suffix : suffixes) {
                    params.add(prefix.replace("%s", inner + suffix));
                    params.add(prefix.replace("%s", innerUp + suffix));
                }
            }
        }

        for (String param : params) {
            // when
            ExcludedPatternsChecker.IsExcluded actual = checker.isExcluded(param);

            // then
            assertTrue("Access to " + param + " is possible!", actual.isExcluded());
        }
    }
