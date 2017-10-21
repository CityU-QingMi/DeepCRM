    protected boolean acceptFile(Object action, UploadedFile file, String filename, String contentType, String inputName, ValidationAware validation) {
        boolean fileIsAcceptable = false;

        // If it's null the upload failed
        if (file == null) {
            String errMsg = getTextMessage(action, "struts.messages.error.uploading", new String[]{inputName});
            if (validation != null) {
                validation.addFieldError(inputName, errMsg);
            }

            if (LOG.isWarnEnabled()) {
                LOG.warn(errMsg);
            }
        } else if (maximumSize != null && maximumSize < file.length()) {
            String errMsg = getTextMessage(action, "struts.messages.error.file.too.large", new String[]{inputName, filename, file.getName(), "" + file.length(), getMaximumSizeStr(action)});
            if (validation != null) {
                validation.addFieldError(inputName, errMsg);
            }

            if (LOG.isWarnEnabled()) {
                LOG.warn(errMsg);
            }
        } else if ((!allowedTypesSet.isEmpty()) && (!containsItem(allowedTypesSet, contentType))) {
            String errMsg = getTextMessage(action, "struts.messages.error.content.type.not.allowed", new String[]{inputName, filename, file.getName(), contentType});
            if (validation != null) {
                validation.addFieldError(inputName, errMsg);
            }

            if (LOG.isWarnEnabled()) {
                LOG.warn(errMsg);
            }
        } else if ((!allowedExtensionsSet.isEmpty()) && (!hasAllowedExtension(allowedExtensionsSet, filename))) {
            String errMsg = getTextMessage(action, "struts.messages.error.file.extension.not.allowed", new String[]{inputName, filename, file.getName(), contentType});
            if (validation != null) {
                validation.addFieldError(inputName, errMsg);
            }

            if (LOG.isWarnEnabled()) {
                LOG.warn(errMsg);
            }
        } else {
            fileIsAcceptable = true;
        }

        return fileIsAcceptable;
    }
