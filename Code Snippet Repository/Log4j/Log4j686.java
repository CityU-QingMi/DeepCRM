    private RollingRandomAccessFileAppender(final String name, final Layout<? extends Serializable> layout,
            final Filter filter, final RollingRandomAccessFileManager manager, final String fileName,
            final String filePattern, final boolean ignoreExceptions,
            final boolean immediateFlush, final int bufferSize, final Advertiser advertiser) {
        super(name, layout, filter, ignoreExceptions, immediateFlush, manager);
        if (advertiser != null) {
            final Map<String, String> configuration = new HashMap<>(layout.getContentFormat());
            configuration.put("contentType", layout.getContentType());
            configuration.put("name", name);
            advertisement = advertiser.advertise(configuration);
        } else {
            advertisement = null;
        }
        this.fileName = fileName;
        this.filePattern = filePattern;
        this.advertiser = advertiser;
    }
