    protected List<String> getProhibitedResultParams() {
        return Arrays.asList(
                DEFAULT_PARAM,
                "namespace",
                "method",
                "encode",
                "parse",
                "location",
                "prependServletContext",
                "suppressEmptyParameters",
                "anchor",
                "statusCode"
        );
    }
