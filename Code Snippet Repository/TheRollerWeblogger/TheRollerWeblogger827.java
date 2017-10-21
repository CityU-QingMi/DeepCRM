    private static URL[] buildURLsArray(String webappDir, String jarsDir) throws Exception {
        // Create collection of URLs needed for classloader
        List urlList = new ArrayList();

        // Add WEB-INF/lib jars
        String libPath = webappDir + FS + "WEB-INF" + FS + "lib";
        addURLs(libPath, urlList);
        
        // Added WEB-INF/classes
        String classesPath = webappDir + FS + "WEB-INF" + FS + "classes" + FS;
        urlList.add(new URL("file://" + classesPath));
        
        // Add additional jars
        addURLs(jarsDir, urlList);
                
        return (URL[])urlList.toArray(new URL[urlList.size()]);  
    }
