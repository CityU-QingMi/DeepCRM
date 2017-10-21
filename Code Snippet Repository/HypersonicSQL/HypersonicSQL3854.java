    void saveAsCsv(String filename) {

        try {
            File      file   = new File(filename);
            CSVWriter writer = new CSVWriter(file, null);
            String[]  col    = gResult.getHead();
            int       width  = col.length;
            Vector    data   = gResult.getData();
            String[]  row;
            int       height = data.size();

            writer.writeHeader(col);

            for (int i = 0; i < height; i++) {
                row = (String[]) data.elementAt(i);

                String[] myRow = new String[row.length];

                for (int j = 0; j < row.length; j++) {
                    String r = row[j];

                    if (r.equals("(null)")) {

                        // null is formatted as (null)
                        r = "";
                    }

                    myRow[j] = r;
                }

                writer.writeData(myRow);
            }

            writer.close();
        } catch (IOException e) {
            throw new RuntimeException("IOError: " + e.getMessage());
        }
    }
