    protected boolean checkExcludePackages(String classPackageName) {
        if(excludePackages != null && excludePackages.length > 0) {
            WildcardHelper wildcardHelper = new WildcardHelper();

            //we really don't care about the results, just the boolean
            Map<String, String> matchMap = new HashMap<>();

            for(String packageExclude : excludePackages) {
                int[] packagePattern = wildcardHelper.compilePattern(packageExclude);
                if(wildcardHelper.match(matchMap, classPackageName, packagePattern)) {
                    return false;
                }
            }
        }
        return true;
    }
