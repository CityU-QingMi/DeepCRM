    protected void processUpload(HttpServletRequest request, String saveDir) throws FileUploadException, UnsupportedEncodingException {
        if (ServletFileUpload.isMultipartContent(request)) {
            for (FileItem item : parseRequest(request, saveDir)) {
                LOG.debug("Found file item: [{}]", item.getFieldName());
                if (item.isFormField()) {
                    processNormalFormField(item, request.getCharacterEncoding());
                } else {
                    processFileField(item);
                }
            }
        }
    }
