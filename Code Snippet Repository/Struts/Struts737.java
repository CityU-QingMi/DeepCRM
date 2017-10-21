    protected void processUpload(HttpServletRequest request, String saveDir) throws Exception {

        // Sanity check that the request is a multi-part/form-data request.
        if (ServletFileUpload.isMultipartContent(request)) {

            // Sanity check on request size.
            boolean requestSizePermitted = isRequestSizePermitted(request);

            // Interface with Commons FileUpload API
            // Using the Streaming API
            ServletFileUpload servletFileUpload = new ServletFileUpload();
            if (maxSizeProvided) {
                servletFileUpload.setSizeMax(maxSize);
            }
            FileItemIterator i = servletFileUpload.getItemIterator(request);

            // Iterate the file items
            while (i.hasNext()) {
                try {
                    FileItemStream itemStream = i.next();

                    // If the file item stream is a form field, delegate to the
                    // field item stream handler
                    if (itemStream.isFormField()) {
                        processFileItemStreamAsFormField(itemStream);
                    }

                    // Delegate the file item stream for a file field to the
                    // file item stream handler, but delegation is skipped
                    // if the requestSizePermitted check failed based on the
                    // complete content-size of the request.
                    else {

                        // prevent processing file field item if request size not allowed.
                        // also warn user in the logs.
                        if (!requestSizePermitted) {
                            addFileSkippedError(itemStream.getName(), request);
                            LOG.warn("Skipped stream '{}', request maximum size ({}) exceeded.", itemStream.getName(), maxSize);
                            continue;
                        }

                        processFileItemStreamAsFileField(itemStream, saveDir);
                    }
                } catch (IOException e) {
                    LOG.warn("Error occurred during process upload", e);
                }
            }
        }
    }
