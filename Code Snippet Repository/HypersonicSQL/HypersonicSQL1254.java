    private void stdprint(String s, boolean queryOutput) {
        if (shared.psStd != null)
            shared.psStd.print(
                    htmlMode ? ("<P>" + SqlFile.escapeHtml(s) + "</P>") : s);

        if (queryOutput && pwQuery != null) {
            pwQuery.print(
                    htmlMode ? ("<P>" + SqlFile.escapeHtml(s) + "</P>") : s);
            pwQuery.flush();
        }
    }
