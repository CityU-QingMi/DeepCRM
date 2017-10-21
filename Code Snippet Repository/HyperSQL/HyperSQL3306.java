    boolean execute(String statement) throws DataAccessPointException {

        if (WTextWrite == null) {
            try {
                WTextWrite = new BufferedWriter(new FileWriter(sFileName));
            } catch (IOException e) {
                throw new DataAccessPointException(e.getMessage());
            }
        }

        try {
            WTextWrite.write(statement + "\n");
            WTextWrite.flush();
        } catch (IOException e) {
            throw new DataAccessPointException(e.getMessage());
        }

        return true;
    }
