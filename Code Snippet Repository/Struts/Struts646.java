    protected String getAdditionalPackages() {
        List<String> packages = new LinkedList<>();
        packages.add("org.apache.struts2.static");
        packages.add("template");
        packages.add("static");

        if (devMode) {
            packages.add("org.apache.struts2.interceptor.debugging");
        }

        return StringUtils.join(packages.iterator(), ' ');
    }
