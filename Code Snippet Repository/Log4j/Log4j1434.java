    @PluginFactory
    public static ScriptFile createScript(
            // @formatter:off
            @PluginAttribute("name") String name,
            @PluginAttribute("language") String language,
            @PluginAttribute("path") final String filePathOrUri,
            @PluginAttribute("isWatched") final Boolean isWatched,
            @PluginAttribute("charset") final Charset charset) {
            // @formatter:on
        if (filePathOrUri == null) {
            LOGGER.error("No script path provided for ScriptFile");
            return null;
        }
        if (name == null) {
            name = filePathOrUri;
        }
        final URI uri = NetUtils.toURI(filePathOrUri);
        final File file = FileUtils.fileFromUri(uri);
        if (language == null && file != null) {
            final String fileExtension = FileUtils.getFileExtension(file);
            if (fileExtension != null) {
                final ExtensionLanguageMapping mapping = ExtensionLanguageMapping.getByExtension(fileExtension);
                if (mapping != null) {
                    language = mapping.getLanguage();
                }
            }
        }
        if (language == null) {
            LOGGER.info("No script language supplied, defaulting to {}", DEFAULT_LANGUAGE);
            language = DEFAULT_LANGUAGE;
        }

        final Charset actualCharset = charset == null ? Charset.defaultCharset() : charset;
        String scriptText;
        try (final Reader reader = new InputStreamReader(
                file != null ? new FileInputStream(file) : uri.toURL().openStream(), actualCharset)) {
            scriptText = IOUtils.toString(reader);
        } catch (final IOException e) {
            LOGGER.error("{}: language={}, path={}, actualCharset={}", e.getClass().getSimpleName(),
                    language, filePathOrUri, actualCharset);
            return null;
        }
        final Path path = file != null ? Paths.get(file.toURI()) : Paths.get(uri);
        if (path == null) {
            LOGGER.error("Unable to convert {} to a Path", uri.toString());
            return null;
        }
        return new ScriptFile(name, path, language, isWatched == null ? Boolean.FALSE : isWatched, scriptText);
    }
