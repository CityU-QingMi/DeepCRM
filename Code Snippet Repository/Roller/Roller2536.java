    private String createFileName(Weblog weblog, String title, String contentType) {
        
        if (weblog == null) {
            throw new IllegalArgumentException("weblog cannot be null");
        }
        if (contentType == null) {
            throw new IllegalArgumentException("contentType cannot be null");
        }
        
        String fileName;
        
        // Determine the extension based on the contentType. This is a hack.
        // The info we need to map from contentType to file extension is in 
        // JRE/lib/content-type.properties, but Java Activation doesn't provide 
        // a way to do a reverse mapping or to get at the data.
        String[] typeTokens = contentType.split("/");
        String ext = typeTokens[1];
        
        if (title != null && !title.trim().equals("")) {
            // We've got a title, so use it to build file name
            StringTokenizer toker = new StringTokenizer(title);
            String tmp = null;
            int count = 0;
            while (toker.hasMoreTokens() && count < 5) {
                String s = toker.nextToken();
                s = s.toLowerCase();
                tmp = (tmp == null) ? s : tmp + "_" + s;
                count++;
            }
            if (!tmp.endsWith("." + ext)) {
                fileName = tmp + "." + ext;
            } else {
                fileName = tmp;
            }            
        } else {            
            // No title or text, so instead we'll use the item's date
            // in YYYYMMDD format to form the file name
            SimpleDateFormat sdf = new SimpleDateFormat();
            sdf.applyPattern("yyyyMMddHHSS");
            fileName = weblog.getHandle()+"-"+sdf.format(new Date())+"."+ext;
        }
        
        return fileName;
    }
