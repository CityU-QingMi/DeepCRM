    public static void preprocessBatch(File sourceDir, File targetDir,
            String[] fileNames, String altExt, String encoding, int options,
            String defines, IResolver resolver) throws PreprocessorException {

        for (int i = 0; i < fileNames.length; i++) {
            String fileName = fileNames[i];

            try {
                preprocessFile(sourceDir, targetDir, fileName, altExt, encoding,
                        options, defines, resolver);
            } catch (PreprocessorException ppe) {

                if (!Option.isVerbose(options)) {
                    log(fileName + " ... not modified, " + ppe.getMessage());
                }

                throw ppe;
            }
        }
    }
