    private List<String> jar(JarInputStream jarStream) throws IOException {
        List<String> classNames = new ArrayList<>();

        JarEntry entry;
        while ((entry = jarStream.getNextJarEntry()) != null) {
            if (entry.isDirectory() || !entry.getName().endsWith(".class")) {
                continue;
            }
            String className = entry.getName();
            className = className.replaceFirst(".class$", "");

            //war files are treated as .jar files, so takeout WEB-INF/classes
            className = StringUtils.removeStart(className, "WEB-INF/classes/"); 

            className = className.replace('/', '.');
            classNames.add(className);
        }

        return classNames;
    }
