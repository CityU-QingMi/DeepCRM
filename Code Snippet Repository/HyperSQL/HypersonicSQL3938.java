    public void setTableHeader(JTableHeader tableHeader) {

        if (this.tableHeader != null) {
            this.tableHeader.removeMouseListener(mouseListener);

            TableCellRenderer defaultRenderer =
                this.tableHeader.getDefaultRenderer();

            if (defaultRenderer instanceof SortableHeaderRenderer) {
                this.tableHeader.setDefaultRenderer(
                    ((SortableHeaderRenderer) defaultRenderer)
                        .tableCellRenderer);
            }
        }

        this.tableHeader = tableHeader;

        if (this.tableHeader != null) {
            this.tableHeader.addMouseListener(mouseListener);
            this.tableHeader.setDefaultRenderer(
                new SortableHeaderRenderer(
                    this.tableHeader.getDefaultRenderer()));
        }
    }
