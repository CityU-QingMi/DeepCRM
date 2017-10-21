    public void save(String fileString) throws Exception {

// oj@openoffice.org
        fa.createParentDirs(fileString);
        fa.removeElement(fileString);

        OutputStream        fos = fa.openOutputStreamElement(fileString);
        FileAccess.FileSync outDescriptor = fa.getFileSync(fos);
        String name = HsqlDatabaseProperties.PRODUCT_NAME + " "
                      + HsqlDatabaseProperties.THIS_FULL_VERSION;

        stringProps.store(fos, name);
        fos.flush();
        outDescriptor.sync();
        fos.close();

        outDescriptor = null;
        fos           = null;
    }
