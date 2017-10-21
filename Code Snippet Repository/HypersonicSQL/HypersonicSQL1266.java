    private void condlPrint(String s, boolean printHtml) {
        if ((printHtml && !htmlMode) || (htmlMode && !printHtml)) return;

        if (shared.psStd != null) shared.psStd.print(s);

        if (pwQuery != null) {
            pwQuery.print(s);
            pwQuery.flush();
        }
    }
