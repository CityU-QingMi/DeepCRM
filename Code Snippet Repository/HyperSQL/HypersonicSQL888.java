    public void registerLobForResult(Result result) {

        RowSetNavigator navigator = result.getNavigator();

        if (navigator == null) {
            registerLobsForRow((Object[]) result.valueData);
        } else {
            while (navigator.next()) {
                Object[] data = navigator.getCurrent();

                registerLobsForRow(data);
            }

            navigator.reset();
        }

        resultLobs.clear();
    }
