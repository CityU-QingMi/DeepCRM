    @Inject(value = XWorkConstants.OGNL_EXCLUDED_PACKAGE_NAME_PATTERNS, required = false)
    public void setExcludedPackageNamePatterns(String commaDelimitedPackagePatterns) {
        Set<String> packagePatterns = TextParseUtil.commaDelimitedStringToSet(commaDelimitedPackagePatterns);
        Set<Pattern> packageNamePatterns = new HashSet<>();

        for (String pattern : packagePatterns) {
            packageNamePatterns.add(Pattern.compile(pattern));
        }

        excludedPackageNamePatterns = Collections.unmodifiableSet(packageNamePatterns);
    }
