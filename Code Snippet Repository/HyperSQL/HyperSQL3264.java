        public Component getTableCellRendererComponent(JTable table,
                Object value, boolean isSelected, boolean hasFocus, int row,
                int column) {

            Component c =
                tableCellRenderer.getTableCellRendererComponent(table, value,
                    isSelected, hasFocus, row, column);

            if (c instanceof JLabel) {
                JLabel l = (JLabel) c;

                l.setHorizontalTextPosition(JLabel.LEFT);

                int modelColumn = table.convertColumnIndexToModel(column);

                l.setIcon(getHeaderRendererIcon(modelColumn,
                                                l.getFont().getSize()));
            }

            return c;
        }
