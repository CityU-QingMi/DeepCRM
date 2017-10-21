    public static Term getTerm(String field, String input) {
        if (input == null || field == null) {
            return null;
        }
        Analyzer analyzer = IndexManagerImpl.getAnalyzer();
        Term term = null;
        try {
            TokenStream tokens = analyzer.tokenStream(field, new StringReader(input));
            CharTermAttribute termAtt = tokens.addAttribute(CharTermAttribute.class);
            tokens.reset();

            if (tokens.incrementToken()) {
                String termt = termAtt.toString();
                term = new Term(field, termt);
            }
        } catch (IOException e) {
            // ignored
        }
        return term;
    }
