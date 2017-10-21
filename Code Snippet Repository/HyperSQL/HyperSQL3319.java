    public void defineCSV(String csvExpressions)
    throws PreprocessorException {
        if (csvExpressions != null) {
            csvExpressions = csvExpressions + ',';

            int start = 0;
            int len   = csvExpressions.length();

            while (start < len) {
                int    end  = csvExpressions.indexOf(',', start);
                String expr = csvExpressions.substring(start, end).trim();

                if (expr.length() > 0) {
                    defineSingle(expr);
                }

                start = end + 1;
            }
        }
    }
