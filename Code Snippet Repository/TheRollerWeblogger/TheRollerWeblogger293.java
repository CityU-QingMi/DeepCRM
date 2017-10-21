    private String loadTemplateFile(File templateFile) {
        // Continue reading theme even if problem encountered with one file
        if (!templateFile.exists() && !templateFile.canRead()) {
            return null;
        }

        char[] chars;
        int length;
        try {
            chars = new char[(int) templateFile.length()];
            FileInputStream stream = new FileInputStream(templateFile);
            InputStreamReader reader = new InputStreamReader(stream, "UTF-8");
            length = reader.read(chars);
        } catch (Exception noprob) {
            log.error("Exception reading theme [" + this.getName()
                    + "] template file [" + templateFile + "]");
            if (log.isDebugEnabled()) {
                log.debug(noprob);
            }
            return null;
        }

        return new String(chars, 0, length);
    }
