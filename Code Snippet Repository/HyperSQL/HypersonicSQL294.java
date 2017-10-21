    void rewind(int position) {

        if (position == scanner.getTokenPosition()) {
            return;
        }

        scanner.position(position);

        if (isRecording) {
            int i = recordedStatement.size() - 1;

            for (; i >= 0; i--) {
                Token token = (Token) recordedStatement.get(i);

                if (token.position < position) {
                    break;
                }
            }

            recordedStatement.setSize(i + 1);
        }

        read();
    }
