    public ServerProperties(int protocol, File file) throws IOException {

        FileInputStream fis = null;

        try {
            fis = new FileInputStream(file);

            stringProps.load(fis);
        } finally {
            if (fis != null) {
                fis.close();
            }
        }

        this.protocol = protocol;
    }
