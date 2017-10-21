    private boolean needScanJar(ClassLoader loader, ClassLoader webappLoader,
                                String jarPath) {
        if (loader == webappLoader) {
            // JARs under WEB-INF/lib must be scanned unconditionally according
            // to the spec.
            return true;
        } else {
            String jarName = jarPath;
            int slash = jarPath.lastIndexOf('/');
            if (slash >= 0) {
                jarName = jarPath.substring(slash + 1);
            }
            return (!noTldJars.contains(jarName));
        }
    }
