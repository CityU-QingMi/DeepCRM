    protected void createOutputDir() {
        String path = null;
        if (isTagFile()) {
            String tagName = tagInfo.getTagClassName();
            path = tagName.replace('.', File.separatorChar);
            path = path.substring(0, path.lastIndexOf(File.separatorChar));
        } else {
            path = getServletPackageName().replace('.',File.separatorChar);
        }

            // Append servlet or tag handler path to scratch dir
            try {
                File base = options.getScratchDir();
                baseUrl = base.toURI().toURL();
                outputDir = base.getAbsolutePath() + File.separator + path + 
                    File.separator;
                if (!makeOutputDir()) {
                    throw new IllegalStateException(Localizer.getMessage("jsp.error.outputfolder"));
                }
            } catch (MalformedURLException e) {
                throw new IllegalStateException(Localizer.getMessage("jsp.error.outputfolder"), e);
            }
    }
