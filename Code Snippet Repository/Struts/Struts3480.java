    private void setEncoding() {
        String encoding = null;

        try {
            encoding = defaultEncoding;

            if (encoding != null) {
                //NB: This should never be called at the same time as the constructor for
                //ServletMultiPartRequest, as it can cause problems.
                //See javadoc for MultipartRequest.setEncoding()
                http.utils.multipartrequest.MultipartRequest.setEncoding(encoding);
            } else {
                http.utils.multipartrequest.MultipartRequest.setEncoding("UTF-8");
            }
        } catch (IllegalArgumentException e) {
            if (LOG.isInfoEnabled()) {
        	    LOG.info("Could not get encoding property 'struts.i18n.encoding' for file upload.  Using system default");
            }
        } catch (UnsupportedEncodingException e) {
            LOG.error("Encoding " + encoding + " is not a valid encoding.  Please check your struts.properties file.");
        }
    }
