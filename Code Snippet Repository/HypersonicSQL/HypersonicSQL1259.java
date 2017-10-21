    private Token commandFromHistory(int inIndex) throws BadSpecial {
        int index = inIndex;  // Just to quiet compiler warnings.

        if (history == null)
            throw new BadSpecial(SqltoolRB.history_unavailable.getString());
        if (index == 0)
            throw new BadSpecial(SqltoolRB.history_number_req.getString());
        if (index > 0) {
            // Positive command# given
            index -= oldestHist;
            if (index < 0)
                throw new BadSpecial(
                        SqltoolRB.history_backto.getString(oldestHist));
            if (index >= history.size())
                throw new BadSpecial(SqltoolRB.history_upto.getString(
                       history.size() + oldestHist - 1));
        } else {
            // Negative command# given
            index += history.size();
            if (index < 0)
                throw new BadSpecial(
                        SqltoolRB.history_back.getString(history.size()));
        }
        return history.get(index);
    }
