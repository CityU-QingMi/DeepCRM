    private void saveDocument(Object target) throws PreprocessorException {
        try {
            if (this.document.size() > 0) {
                this.document.save(target, this.encoding);
            }
        } catch (UnsupportedEncodingException uee) {
            throw new PreprocessorException("Unsupported encoding \""
                    + this.encoding + "\" writing \"" + target
                    + "\""); // NOI18N
        } catch (IOException ioe) {
            throw new PreprocessorException("Unable to write to \""
                    + target + "\": " + ioe); // NOI18N
        }
    }
