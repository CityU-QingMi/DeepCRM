        private void createTextFile() {

            PrintStream textFile = null;

            try {
                String completeFileName = m_name + ".csv";

                FileUtil.getFileUtil().delete(completeFileName);

                textFile = new PrintStream(
                    FileUtil.getFileUtil().openOutputStreamElement(
                        completeFileName));

                new java.io.File(completeFileName).deleteOnExit();
            } catch (IOException ex) {
                fail(ex.toString());
            }

            for (int row = 0; row < m_data.length; ++row) {
                StringBuffer buf      = new StringBuffer();
                int          colCount = m_data[row].length;

                for (int col = 0; col < colCount; ++col) {
                    buf.append(m_data[row][col].toString());

                    if (col + 1 != colCount) {
                        buf.append(m_separator);
                    }
                }

                textFile.println(buf.toString());
            }

            textFile.close();
        }
