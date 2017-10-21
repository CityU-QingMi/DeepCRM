    public void parse(HttpServletRequest servletRequest, String saveDir) throws IOException {
        //this needs to be synchronised, as we should not change the encoding at the same time as
        //calling the constructor.  See javadoc for MultipartRequest.setEncoding().
        synchronized (this) {
            setEncoding();
            if (maxSizeProvided){
                int intMaxSize = (maxSize >= Integer.MAX_VALUE ? Integer.MAX_VALUE : Long.valueOf(maxSize).intValue());
            	multi = new ServletMultipartRequest(servletRequest, saveDir, intMaxSize);
            }else{
            	multi = new ServletMultipartRequest(servletRequest, saveDir);
            }
        }
    }
