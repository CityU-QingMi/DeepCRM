    protected Test<ClassFinder.ClassInfo> getActionClassTest() {
        return new Test<ClassFinder.ClassInfo>() {
            public boolean test(ClassFinder.ClassInfo classInfo) {

                // Why do we call includeClassNameInActionScan here, when it's
                // already been called to in the initial call to ClassFinder?
                // When some action class passes our package filter in that step,
                // ClassFinder automatically includes parent classes of that action,
                // such as com.opensymphony.xwork2.ActionSupport.  We repeat the
                // package filter here to filter out such results.
                boolean inPackage = includeClassNameInActionScan(classInfo.getName());
                boolean nameMatches = matchesSuffix(classInfo.getName());

                try {
                    return inPackage && (nameMatches || (checkImplementsAction && com.opensymphony.xwork2.Action.class.isAssignableFrom(classInfo.get())));
                } catch (ClassNotFoundException ex) {
                    LOG.error("Unable to load class [{}]", classInfo.getName(), ex);
                    return false;
                }
            }

            private boolean matchesSuffix(String name) {
                for (String suffix : actionSuffix) {
                    if (name.endsWith(suffix)) {
                        return true;
                    }
                }
                return false;
            }
        };
    }
