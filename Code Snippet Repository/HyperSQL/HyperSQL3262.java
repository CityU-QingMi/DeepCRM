        public void mouseClicked(MouseEvent e) {

            JTableHeader     h           = (JTableHeader) e.getSource();
            TableColumnModel columnModel = h.getColumnModel();
            int              viewColumn  = h.columnAtPoint(e.getPoint());
            int column = columnModel.getColumn(viewColumn).getModelIndex();

            if (column != -1) {
                int status = getSortingStatus(column);

                if (!e.isControlDown()) {
                    cancelSorting();
                }

                // Cycle the sorting states through {NOT_SORTED, ASCENDING, DESCENDING} or
                // {NOT_SORTED, DESCENDING, ASCENDING} depending on whether shift is pressed.
                status = status + (e.isShiftDown() ? -1
                                                   : 1);
                status = (status + 4) % 3 - 1;    // signed mod, returning {-1, 0, 1}

                setSortingStatus(column, status);
            }
        }
