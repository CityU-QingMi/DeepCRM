    public void parse(HttpServletRequest request, String saveDir) throws IOException {
        try {
            setLocale(request);
            processUpload(request, saveDir);
        } catch (FileUploadException e) {
            LOG.warn("Request exceeded size limit!", e);
            LocalizedMessage errorMessage;
            if(e instanceof FileUploadBase.SizeLimitExceededException) {
                FileUploadBase.SizeLimitExceededException ex = (FileUploadBase.SizeLimitExceededException) e;
                errorMessage = buildErrorMessage(e, new Object[]{ex.getPermittedSize(), ex.getActualSize()});
            } else {
                errorMessage = buildErrorMessage(e, new Object[]{});
            }

            if (!errors.contains(errorMessage)) {
                errors.add(errorMessage);
            }
        } catch (Exception e) {
            LOG.warn("Unable to parse request", e);
            LocalizedMessage errorMessage = buildErrorMessage(e, new Object[]{});
            if (!errors.contains(errorMessage)) {
                errors.add(errorMessage);
            }
        }
    }
