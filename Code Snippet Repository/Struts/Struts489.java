    private List<ValidatorConfig> loadFile(String fileName, Class clazz, boolean checkFile) {
        List<ValidatorConfig> retList = Collections.emptyList();

        URL fileUrl = ClassLoaderUtil.getResource(fileName, clazz);

        if ((checkFile && fileManager.fileNeedsReloading(fileUrl)) || !validatorFileCache.containsKey(fileName)) {
            try (InputStream is = fileManager.loadFile(fileUrl)) {
                if (is != null) {
                    retList = new ArrayList<>(validatorFileParser.parseActionValidatorConfigs(validatorFactory, is, fileName));
                }
            } catch (IOException e) {
                LOG.error("Caught exception while loading file {}", fileName, e);
            }

            validatorFileCache.put(fileName, retList);
        } else {
            retList = validatorFileCache.get(fileName);
        }

        return retList;
    }
