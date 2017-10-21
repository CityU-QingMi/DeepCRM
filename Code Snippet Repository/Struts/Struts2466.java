    private void scanDir(File dir, List<String> classNames, String packageName) {
        File[] files = dir.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                scanDir(file, classNames, packageName + file.getName() + ".");
            } else if (file.getName().endsWith(".class")) {
                String name = file.getName();
                name = name.replaceFirst(".class$", "");
                // Classes packaged in an exploded .war (e.g. in a VFS file system) should not
                // have WEB-INF.classes in their package name.
                classNames.add(StringUtils.removeStart(packageName, "WEB-INF.classes.") + name);
            }
        }
    }
