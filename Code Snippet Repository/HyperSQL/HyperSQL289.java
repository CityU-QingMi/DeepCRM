    private void showHistory() throws BadSpecial {
        if (history == null)
            throw new BadSpecial(SqltoolRB.history_unavailable.getString());
        if (history.size() < 1)
            throw new BadSpecial(SqltoolRB.history_none.getString());
        if (shared.psStd == null) return;
          // Input can be dual-purpose, i.e. the script can be intended for
          // both interactive and non-interactive usage.
        Token token;
        for (int i = 0; i < history.size(); i++) {
            token = history.get(i);
            shared.psStd.println("#" + (i + oldestHist) + " or "
                    + (i - history.size()) + ':');
            shared.psStd.println(token.reconstitute());
        }
        if (buffer != null)
            shared.psStd.println(SqltoolRB.editbuffer_contents.getString(
                    buffer.reconstitute()));

        shared.psStd.println();
        shared.psStd.println(SqltoolRB.buffer_instructions.getString());
    }
