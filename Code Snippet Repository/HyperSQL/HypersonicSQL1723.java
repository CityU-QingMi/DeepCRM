    protected byte[] getGZipData() throws SQLException {

        byte[] bytes = gZipData();

        if (bytes != null) {
            return bytes;
        }

        if (this.domResult != null) {
            DOMSource source = new DOMSource(domResult.getNode(),
                domResult.getSystemId());
            OutputStream os     = setBinaryStreamImpl();
            StreamResult result = new StreamResult(os);

            try {
                JDBCSQLXML.identityTransformer.transform(source, result);
            } catch (TransformerException ex) {
                throw Exceptions.transformFailed(ex);
            }

            try {
                os.close();
            } catch (IOException ex) {
                throw Exceptions.transformFailed(ex);
            }
        }

        if (this.outputStream == null) {
            throw Exceptions.notReadable("No Data.");
        } else if (!this.outputStream.isClosed()) {
            throw Exceptions.notReadable(
                "Stream used for writing must be closed but is still open.");
        } else if (this.outputStream.isFreed()) {
            throw Exceptions.notReadable(
                "Stream used for writing was freed and is no longer valid.");
        }

        try {
            setGZipData(this.outputStream.toByteArray());

            return gZipData();
        } catch (IOException ex) {
            throw Exceptions.notReadable();
        } finally {
            this.freeOutputStream();
        }
    }
