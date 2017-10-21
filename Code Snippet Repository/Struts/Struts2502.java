    protected boolean checkActionPackages(String classPackageName) {
        if (actionPackages != null) {
            for (String packageName : actionPackages) {
                String strictPackageName = packageName + ".";
                if (classPackageName.equals(packageName) || classPackageName.startsWith(strictPackageName)) {
                    return true;
                }
            }
        }
        return false;
    }
