    private void stdprintln(String s, boolean queryOutput) {
        shared.psStd.println(
                htmlMode ? ("<P>" + SqlFile.escapeHtml(s) + "</P>") : s);

        if (queryOutput && pwQuery != null) {
            pwQuery.println(
                    htmlMode ? ("<P>" + SqlFile.escapeHtml(s) + "</P>") : s);
            pwQuery.flush();
        }
    }
