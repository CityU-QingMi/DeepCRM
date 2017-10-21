    public void addLobResult(ResultLob result) {

        Result current = this;

        while (current.lobResults != null) {
            current = current.lobResults;
        }

        current.lobResults = result;

        lobCount++;
    }
