    private Document loadInclude(String path) throws PreprocessorException {
        Document include = new Document();
        File     file    = toCanonicalOrAbsoluteFile(path);

        try {
            return include.load(file, this.encoding);
        } catch (UnsupportedEncodingException uee) {
            throw new PreprocessorException("Unsupported encoding \""
                    + this.encoding + "\" loading include \"" + file
                    + "\""); // NOI18N
        } catch (IOException ioe) {
            throw new PreprocessorException("Unable to load include \""
                    + file + "\": " + ioe); // NOI18N
        }
    }
