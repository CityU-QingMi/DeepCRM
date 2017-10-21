    protected long getLastModifiedDate(String fileName) {
        URL resource = Thread.currentThread().getContextClassLoader().getResource(fileName);
        
        // Get the absolute path.
        String filePath = (resource == null) ? fileName : URLDecoder.decode(resource.getFile()); ;
        
        // If the jobs file is inside a jar point to the jar file (to get it modification date).
        // Otherwise continue as usual.
        int jarIndicator = filePath.indexOf('!');
        
        if (jarIndicator > 0) {
            filePath = filePath.substring(5, filePath.indexOf('!'));
        }

        File file = new File(filePath);
        
        if(!file.exists()) {
            return -1;
        } else {
            return file.lastModified();
        }
    }
