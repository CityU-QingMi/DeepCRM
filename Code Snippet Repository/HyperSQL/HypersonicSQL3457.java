        public final Object[][] appendRowData(Object[] rowData) {

            Object[][] newData = new Object[m_data.length + 1][rowData.length];

            for (int row = 0; row < m_data.length; ++row) {
                newData[row] = m_data[row];
            }

            newData[m_data.length] = rowData;
            m_data                 = newData;

            return m_data;
        }
