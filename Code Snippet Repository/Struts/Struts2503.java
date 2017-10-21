    protected boolean checkPackageLocators(String classPackageName) {
        if (packageLocators != null && !disablePackageLocatorsScanning && classPackageName.length() > 0
                && (packageLocatorsBasePackage == null || classPackageName
                        .startsWith(packageLocatorsBasePackage))) {
            for (String packageLocator : packageLocators) {
                String[] splitted = classPackageName.split("\\.");

                if (StringTools.contains(splitted, packageLocator, false)) {
                    return true;
                }
            }
        }
        return false;
    }
