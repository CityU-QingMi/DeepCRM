    Token[] getRecordedStatement() {

        isRecording = false;

        recordedStatement.remove(recordedStatement.size() - 1);

        Token[] tokens = new Token[recordedStatement.size()];

        recordedStatement.toArray(tokens);

        recordedStatement = null;

        return tokens;
    }
