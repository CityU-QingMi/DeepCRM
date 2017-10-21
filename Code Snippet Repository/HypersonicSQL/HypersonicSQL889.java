    private void registerLobsForRow(Object[] data) {

        for (int i = 0; i < data.length; i++) {
            if (data[i] instanceof BlobDataID) {
                BlobData blob = (BlobDataID) data[i];
                long     id   = blob.getId();

                if (id < 0) {
                    id = resultLobs.get(id);
                }

                data[i] = database.lobManager.getBlob(id);

                // handle invalid id;
            } else if (data[i] instanceof ClobDataID) {
                ClobData clob = (ClobDataID) data[i];
                long     id   = clob.getId();

                if (id < 0) {
                    id = resultLobs.get(id);
                }

                data[i] = database.lobManager.getClob(id);

                // handle invalid id;
            }
        }
    }
