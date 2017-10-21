    private void loadImplementationsInJar(Test test, String parent, File jarfile) {

        try {
            JarEntry entry;
            JarInputStream jarStream = new JarInputStream(new FileInputStream(jarfile));

            while ( (entry = jarStream.getNextJarEntry() ) != null) {
                String name = entry.getName();
                if (!entry.isDirectory() && name.startsWith(parent) && isTestApplicable(test, name)) {
                    addIfMatching(test, name);
                }
            }
        }
        catch (IOException ioe) {
            LOG.error("Could not search jar file '" + jarfile + "' for classes matching criteria: " +
                      test + " due to an IOException", ioe);
        }
    }
