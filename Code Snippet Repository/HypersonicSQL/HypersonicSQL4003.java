    private void loadDocument() throws PreprocessorException {
        try {
            this.document.load(this.documentPath, this.encoding);
        } catch (UnsupportedEncodingException uee) {
            throw new PreprocessorException("Unsupported encoding \""
                    + this.encoding + "\" reading file \"" + this.documentPath
                    + "\""); // NOI18N
        } catch (IOException ioe) {
            throw new PreprocessorException("Unable to read file \""
                    + this.documentPath + "\": " + ioe); // NOI18N
        }
    }
