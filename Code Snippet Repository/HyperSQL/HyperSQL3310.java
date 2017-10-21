    void getTableStructure(TransferTable TTable,
                           DataAccessPoint Dest)
                           throws DataAccessPointException {

        if (!StructureAlreadyParsed) {
            if (WTextRead != null) {
                try {
                    WTextRead.close();

                    WTextRead = null;
                } catch (IOException e) {}
            }

            this.parseFileForTheRest(TTable, Dest);
        }
    }
