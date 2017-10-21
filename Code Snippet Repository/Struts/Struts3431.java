    protected void replaceSystemPackages(Properties properties) {
        //Felix has a way to load the config file and substitution expressions
        //but the method does not have a way to specify the file (other than in an env variable)

        //${jre-${java.specification.version}}
        String systemPackages = (String) properties.get(Constants.FRAMEWORK_SYSTEMPACKAGES);
        String jreVersion = "jre-" + System.getProperty("java.version").substring(0, 3);
        systemPackages = systemPackages.replace("${jre-${java.specification.version}}", (String) properties.get(jreVersion));
        properties.put(Constants.FRAMEWORK_SYSTEMPACKAGES, systemPackages);
    }
