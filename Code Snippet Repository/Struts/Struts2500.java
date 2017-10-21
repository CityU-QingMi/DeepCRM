    @SuppressWarnings("")
    protected Set<Class> findActions() {
        Set<Class> classes = new HashSet<>();
        try {
            if (actionPackages != null || (packageLocators != null && !disablePackageLocatorsScanning)) {

                // By default, ClassFinder scans EVERY class in the specified
                // url set, which can produce spurious warnings for non-action
                // classes that can't be loaded. We pass a package filter that
                // only considers classes that match the action packages
                // specified by the user
                Test<String> classPackageTest = getClassPackageTest();
                List<URL> urls = readUrls();
                ClassFinder finder = buildClassFinder(classPackageTest, urls);

                Test<ClassFinder.ClassInfo> test = getActionClassTest();
                classes.addAll(finder.findClasses(test));
            }
        } catch (Exception ex) {
            LOG.error("Unable to scan named packages", ex);
        }

        return classes;
    }
