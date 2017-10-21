    protected boolean isPackageExcluded(Package targetPackage, Package memberPackage) {
        if (targetPackage == null || memberPackage == null) {
            LOG.warn("The use of the default (unnamed) package is discouraged!");
        }
        
        final String targetPackageName = targetPackage == null ? "" : targetPackage.getName();
        final String memberPackageName = memberPackage == null ? "" : memberPackage.getName();

        for (Pattern pattern : excludedPackageNamePatterns) {
            if (pattern.matcher(targetPackageName).matches() || pattern.matcher(memberPackageName).matches()) {
                return true;
            }
        }

        for (String packageName: excludedPackageNames) {
            if (targetPackageName.startsWith(packageName) || targetPackageName.equals(packageName)
                    || memberPackageName.startsWith(packageName) || memberPackageName.equals(packageName)) {
                return true;
            }
        }

        return false;
    }
