    public void dsvSafe(String s) throws SqlToolError {
        assert pwDsv != null && dsvColDelim != null && dsvRowDelim != null
                && nullRepToken != null:
            "Assertion failed.  \n"
            + "dsvSafe called when DSV settings are incomplete";

        if (s == null) return;

        if (s.indexOf(dsvColDelim) > 0)
            throw new SqlToolError(
                    SqltoolRB.dsv_coldelim_present.getString(dsvColDelim));

        if (s.indexOf(dsvRowDelim) > 0)
            throw new SqlToolError(
                    SqltoolRB.dsv_rowdelim_present.getString(dsvRowDelim));

        if (s.trim().equals(nullRepToken))
            // The trim() is to avoid the situation where the contents of a
            // field "looks like" the null-rep token.
            throw new SqlToolError(
                    SqltoolRB.dsv_nullrep_present.getString(nullRepToken));
    }
